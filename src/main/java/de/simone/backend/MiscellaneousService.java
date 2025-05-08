package de.simone.backend;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Position;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import de.simone.TranslationProvider;
import de.simone.UIUtils;

@ApplicationScoped
@Path("/Miscellaneous")
public class MiscellaneousService {

  public static final String RESOURCES_PATH = "gresources/";
  public static final String RESOURCES_IMAGES = RESOURCES_PATH + "images/";
  public static final String RESOURCES_TRANSLATIONS = RESOURCES_PATH + "translations/";
  public static final String STATIC_RESOURCES_PATH =
      "/META-INF/resources/"; // NOSONAR this value is not Customizable

  // mother fu**er

  public String uploadFile(String folder, String base64Data) {
    String fileN = saveMimeDataToFile(folder, base64Data);
    return "File uploaded " + fileN;
  }

  public Response downloadFile(String filePath) {
    File fileDownload = new File(filePath);
    String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
    Response.ResponseBuilder response = Response.ok(fileDownload);
    response.header("Content-Disposition", "attachment;filename=" + fileName);
    return response.build();
  }

  public static Map<String, String> getEntityFields(Class<? extends TAEntity> clazz) {
    return getEntityFields(clazz, false);
  }

  @GET
  @Path("/getEntityFields/{entityName}")
  @RolesAllowed({"Sitrone.master"})
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Map<String, String> getEntityFields2(@PathParam(value = "entityName") String entityName)
      throws ClassNotFoundException, WebApplicationException {
    // TODO: this is a temp solution to the "i.dont know who to obtain the current
    // directory to scann all classes to
    // find what i want" problem
    String root = System.getProperty("user.dir");
    File rootDir = new File(root + "/target/classes/de/gooddev/backend");
    // is prod entvirontment
    File prodDir = new File(root + "/itrone/de/gooddev/backend");
    if (prodDir.exists()) rootDir = prodDir;

    Collection<File> collection = FileUtils.listFiles(rootDir, new String[] {"class"}, true);
    Optional<File> optional =
        collection.stream().filter(f -> f.getName().equals(entityName + ".class")).findFirst();
    Class clazz1 = null;
    if (!optional.isPresent())
      throw new WebApplicationException(
          "Entity '" + entityName + "' not found.", Response.Status.NOT_FOUND);
    else {
      String clazz = "de.gooddev.backend." + optional.get().getName().replace(".class", "");
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      clazz1 = loader.loadClass(clazz);
      if (!TAEntity.class.isAssignableFrom(clazz1))
        throw new WebApplicationException(
            "Argument '" + clazz + "' is not a valid entity to retrive variables from.",
         Response.Status.NOT_FOUND);
    }
    return getEntityFields(clazz1, false);
  }

  /**
   * return a Map containing (allmost) all declared fields inside the clazz entity argument. the
   * Value for every entry is the text description for the field
   *
   * @param clazz - class to extract the fields of
   * @param withPrefix - true to append the class name as prefix (suitable for qute template)
   * @return the map
   */
  public static Map<String, String> getEntityFields(
      Class<? extends TAEntity> clazz, boolean withPrefix) {
    Map<String, String> variables = new TreeMap<>();
    String prefix = withPrefix ? clazz.getSimpleName() + "." : "";
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      String name = field.getName();
      int mod = field.getModifiers();
      if (name.startsWith("$$_")
          || Modifier.isStatic(mod)
          || Modifier.isInterface(mod)
          || Collection.class.isAssignableFrom(field.getType())
          || TAEntity.class.isAssignableFrom(field.getType())) continue;

      String name2 = prefix + name;
      variables.put(
          name2, TranslationProvider.getTranslation(clazz.getSimpleName() + "." + field.getName()));
    }

    // remove undesired fields
    variables.remove(prefix + "isLive");
    variables.remove(prefix + "secondaryKey");
    variables.remove(prefix + "testCodeParameter");

    return variables;
  }

  public static Map<String, Integer> getEntityFieldLength(Class<? extends TAEntity> clazz) {
    return getEntityFieldLength(clazz, false);
  }

  /**
   * return a Map with key=Field name and value= Field lenght. the lenght is obteined form the @Size
   * anotation inside the entitiy passed as arguent
   *
   * @param clazz - the entity class
   * @param withPrefix - with clazz.getSimpleName(). as prefix
   * @return the Map
   */
  public static Map<String, Integer> getEntityFieldLength(
      Class<? extends TAEntity> clazz, boolean withPrefix) {
    Map<String, Integer> variables = new TreeMap<>();
    String prefix = withPrefix ? clazz.getSimpleName() + "." : "";
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      String name = field.getName();
      Size size = field.getAnnotation(Size.class);
      if (size != null) {
        String name2 = prefix + name;
        variables.put(name2, size.max());
      }
    }
    return variables;
  }

  public static String saveMimeDataToFile(String base64Data) {
    return saveMimeDataToFile(RESOURCES_IMAGES, base64Data);
  }

  /**
   * save the incomming information stored in {@link Base64} format as file. This method parse the
   * base64 data and decide the tipe of file extension based on the data information schema stored
   * in head of the incomming data
   *
   * @param folder - target folder. (muss end with /)
   * @param base64Data - information to store
   * @return the path/file.name assigned by this method
   * @throws Exception
   */
  public static String saveMimeDataToFile(String folder, String base64Data) {
    // parse incommin data format (data:image/png;base64)
    int comaI = base64Data.indexOf(',', 0);
    String dFormat = base64Data.substring(0, comaI);
    String base64 = base64Data.substring(comaI + 1);
    String mime = dFormat.split(":")[1].split(";")[0];
    String ext = mime.split("/")[1];
    String fileN = folder + Long.toHexString(System.currentTimeMillis()) + "." + ext;
    // String fileN = folder + "/" + Long.toHexString(System.currentTimeMillis()) +
    // "." + ext;
    byte[] data = Base64.getDecoder().decode(base64);
    ByteArrayInputStream bais = new ByteArrayInputStream(data);
    saveToFile(bais, fileN);
    return fileN;
  }

  /**
   * read the content of a text file located in static resources folder. {@code
   * /META-INF/resources/}
   *
   * @param fileName - the file to read
   * @return the content
   */
  public static String readScriptFile(String fileName) {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    try (InputStream inputStream =
        loader.getResource(STATIC_RESOURCES_PATH + fileName).openStream()) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String script = reader.lines().collect(Collectors.joining(System.lineSeparator()));
      return script;
    } catch (Exception e) {
      Log.error("", e);
    }
    return "";
  }

  public static String readResourceFile(String fileName) {
    File file = new File(fileName);
    try (FileInputStream inputStream = new FileInputStream(file)) {
      String content = new String(inputStream.readAllBytes());
      return content;
    } catch (Exception e) {
      Log.error("", e);
    }
    return "";
  }

  /**
   * check if the filename exist. if exist, this method will return a new fileName. e.g if fileName
   * terry.txt exis, this method return Terrx_1.txt
   *
   * @param fileName - the origen file name to consider
   */
  private static String getFileName(String fileName) {

    File targetFile = new File(fileName);

    if (targetFile.exists()) {
      String dir = FilenameUtils.getPath(fileName);
      String fbn = FilenameUtils.getBaseName(fileName);
      String fext = FilenameUtils.getExtension(fileName);
      int cnt = 0;
      while (targetFile.exists()) {
        cnt++;
        targetFile = new File(dir + fbn + "_" + cnt + "." + fext);
      }
    }
    return targetFile.getPath();
  }

  /**
   * save the data channeled by the inputStream argument to the {@link
   * MiscellaneousService#RESOURCES_IMAGES} folder in hard drive and return the saved filename path
   *
   * <p>NOTE: this method check if the filaName argument start with {@link
   * MiscellaneousService#RESOURCES_PATH}. if not, automatic add the resource path
   *
   * @param inputStream - data source
   * @param fileName - original filename.
   */
  public static String saveImageToFile(InputStream inputStream, String fileName) {
    String tmp =
        fileName.startsWith(MiscellaneousService.RESOURCES_IMAGES)
            ? fileName
            : MiscellaneousService.RESOURCES_IMAGES + fileName;
    String fileName2 = getFileName(tmp);
    saveToFile(inputStream, fileName2);
    return fileName2;
  }

  // private static void saveToFile(InputStream inputStream, String fileName) {
  //   try {
  //     // if fileName contain directory, create if not exist
  //     java.nio.file.Path path = Paths.get(fileName);
  //     if (path.getNameCount() > 0) {
  //       File dir = path.getParent().toFile();
  //       if (!dir.exists()) dir.mkdirs();
  //     }
  //     byte[] bytes = IOUtils.toByteArray(inputStream);
  //     Files.write(Paths.get(fileName), bytes);
  //   } catch (Exception e) {
  //     Log.error("", e);
  //   }
  // }

  /**
   * Read the File asociated with fileName parameter and convert the binary file into {@link Base64}
   * format. this method also include de header mime schema at start of the converted binary file.
   *
   * @param fileName - the file name to read
   * @return base64 string with header
   */
  public static String readMimeDataFromFile(String fileName) {
    String filename2 = fileName;
    boolean userFileInput = false;
    // is file static?
    if (fileName.startsWith(RESOURCES_PATH)) {
      userFileInput = true;
      filename2 = System.getProperty("user.dir") + File.separator + fileName;
    } else {
      filename2 = STATIC_RESOURCES_PATH + fileName;
    }

    File file = new File(filename2);
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    try (InputStream inputStream =
        userFileInput ? new FileInputStream(file) : loader.getResource(filename2).openStream()) {
      byte[] data = inputStream.readAllBytes();
      String cntT = Files.probeContentType(file.toPath());
      String base64 = Base64.getEncoder().encodeToString(data);
      String finalData = "data:" + cntT + ";base64," + base64;
      return finalData;
    } catch (Exception e) {
      Log.error(e); // single line log
    }
    return null;
  }

  
  public static void deleteFile(String fileName) {
    String filename2 = System.getProperty("user.dir") + "/" + fileName;
    File file = new File(filename2);
    boolean b = file.delete();
    if (!b) Log.error("Error trying to delete the file '" + filename2 + "'");
  }

  /**
   * extract the mimetype from the base64 image passes as argument.
   *
   * @param image64 - the image
   * @return the mimetype
   */
  public static String getMimeType(String image64) {
    // Pattern patternInsert = Pattern.compile("(data:([^;]+);base64,)");
    // Matcher matcher = patternInsert.matcher(image64.substring(0, 100));
    // System.out.println("MiscellaneousService.() " + image64.substring(0, 100));
    // String mime = matcher.group(1);
    // System.out.println("MiscellaneousService.getMimeType() " + mime);

    String s1 = image64.substring(0, 100);
    int i0 = s1.indexOf(':');
    int i1 = s1.indexOf(';');
    String mime = s1.substring(i0 + 1, i1);
    return mime;
  }

  
  /**
   * save the content of the inputStream to a file.
   *
   * @param inputStream - the inputStream
   * @param fileName - the file name
   * @return the relative path to the saved file z.B public/files (no pre or pos /)
   */
  public static String saveToFile(
      InputStream inputStream, String fileName) {

    // fail safe: remove posible directory reference in fileName
    File file = new File(fileName);

    String fileType = "image";
    if (UIUtils.isFileMemberOf(fileName, UIUtils.VALID_DOCUMENTS)) fileType = "file";

    String fullFn = "media/" + fileType + "/" + file.getName();

    String fileName2 = getFileName(fullFn);

    try {
      // if fileName2 contain directory, create if not exist
      java.nio.file.Path path = Paths.get(fileName2);
      if (path.getNameCount() > 0) {
        File dir = path.getParent().toFile();
        if (!dir.exists()) dir.mkdirs();
      }
      byte[] bytes = IOUtils.toByteArray(inputStream);
      Files.write(Paths.get(fileName2), bytes);
    } catch (Exception e) {
      Log.error(e);
    }
    return fileName2;
  }

  /**
   * create and return a Component icon for Documents files
   *
   * @param fileName - the file name
   * @param height - the height of the icon. the with will be calculated
   * @return the icon
   */
  public static Component createFileIcon(String fileName, int height) {
    Div corner = new Div();
    corner.addClassNames(Background.CONTRAST_50, Display.FLEX, Position.ABSOLUTE);
    corner.getStyle().set("right", "0px").set("top", "0px");

    corner.setHeight(30, Unit.PERCENTAGE);
    corner.setWidth(30, Unit.PERCENTAGE);

    Div fileIcon = new Div(corner, new Span(fileName.substring(fileName.lastIndexOf(".") + 1)));
    fileIcon.addClassNames(
        AlignItems.END,
        Background.PRIMARY,
        BorderRadius.MEDIUM,
        Display.FLEX,
        FontSize.XXSMALL,
        FontWeight.MEDIUM,
        Height.LARGE,
        JustifyContent.CENTER,
        Padding.XSMALL,
        Position.RELATIVE,
        TextColor.PRIMARY_CONTRAST,
        Width.MEDIUM);
    fileIcon.getStyle().set("clip-path", "polygon(0% 0%, 70% 0%, 100% 30%, 100% 100%, 0% 100%)");
    fileIcon.setWidth(height * 0.75 + "px");
    fileIcon.setHeight(height + "px");
    return fileIcon;
  }

  public static String getNormalizeString(String stringToNormalize) {
    // replace all but chars, nums and dot
    String nString = stringToNormalize.replaceAll("[^\\w\\.\\/]", "_");
    // remove all but the last dot
    nString = nString.replaceAll("\\.(?=.*\\.)", "_");
    return nString;
  }

}
