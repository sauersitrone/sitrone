package de.simone.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.customfield.CustomFieldVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import de.simone.UIUtils;
import de.simone.backend.MiscellaneousService;
import de.simone.components.Layout;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoaderSimple extends CustomField<String> {

  private Upload upload;
  private String fileName;
  private MemoryBuffer fileBuffer;
  private List<String> filesExt;
  private Component documentIcon;
  private Image imageImage;
  private Button deleteButton;
  private Layout avatarLayout;
  private String placeHolder = UIUtils.YOUR_IMAGE_HERE;

  public static final int ONE_MB = 1;
  public static final int FOUR_MB = 4;
  public static final int ONE_HUNDRED_MB = 100;

  public static final int IMAGE_SIZE = 2;
  public static final int DOCUMENT_SIZE = 10;
  public static final int VIDE_SIZE = 100;

  public static final long FIELD_HELP = -1;
  public static final long STANDARD_HELP = -2;
  public static final long NO_HELP = -3;

  public FileLoaderSimple() {
    //
  }

  public FileLoaderSimple(String fieldName) {
    this(fieldName, ONE_MB, STANDARD_HELP, UIUtils.VALID_IMAGES);
  }

  public FileLoaderSimple(String fieldName, int sizeInMb) {
    this(fieldName, sizeInMb, STANDARD_HELP, UIUtils.VALID_IMAGES);
  }

  public FileLoaderSimple(String fieldName, int sizeInMb, String... extensions) {
    this(fieldName, sizeInMb, STANDARD_HELP, extensions);
  }

  public FileLoaderSimple(String fieldName, int sizeInMb, long helpType, String... extensions) {
    this.filesExt = Arrays.asList(extensions);
    this.imageImage = UIUtils.getMediumImage(null);

    this.documentIcon =
        MiscellaneousService.createFileIcon(
            "Terry.doc", 80); // icon with .doc file type. visual purpose only

    Button uploadButton = new Button(getTranslation("FileLoader.upload"));
    uploadButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    upload = UIUtils.getFileUpload(sizeInMb, extensions);
    upload.setDropAllowed(false);
    upload.setUploadButton(uploadButton);
    upload.addSucceededListener(
        event -> {
          fileBuffer = (MemoryBuffer) upload.getReceiver();
          fileName = event.getFileName();
          String source = MiscellaneousService.saveToFile(fileBuffer.getInputStream(), fileName);
          updateImage(source);
          setValue(source);
        });
    upload.addFileRejectedListener(
        evt -> {
          setErrorMessage(evt.getErrorMessage());
          setInvalid(true);
        });

    deleteButton = new Button(getTranslation("FileLoader.delete"));
    deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
    deleteButton.addClickListener(e-> deleteField());

    // is this loader a image or video Loader?
    boolean isImageOVideo =
        filesExt.containsAll(Arrays.asList(UIUtils.VALID_IMAGES))
            || filesExt.containsAll(Arrays.asList(UIUtils.VALID_VIDEOS));
    imageImage.setVisible(isImageOVideo);
    documentIcon.setVisible(!isImageOVideo);

    avatarLayout = new Layout(imageImage, documentIcon, upload, deleteButton);
    avatarLayout.addClassNames(Margin.Bottom.XSMALL, Margin.Top.NONE);
    avatarLayout.setAlignItems(Layout.AlignItems.CENTER);
    avatarLayout.setGap(Layout.Gap.MEDIUM);

    setLabel(getTranslation(fieldName));
    setId(fieldName);
    if (helpType == FIELD_HELP) {
      setHelperComponent(UIUtils.getHelperComponent(fieldName));
      addThemeVariants(CustomFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    if (helpType == STANDARD_HELP) {
      String parm = filesExt.stream().collect(Collectors.joining(", "));
      setHelperComponent(UIUtils.getHelperComponent("FileLoader", sizeInMb, parm));
      addThemeVariants(CustomFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }

    setPlaceHolder(UIUtils.YOUR_IMAGE_HERE);
    add(avatarLayout);
  }

  public void deleteField() {
    this.fileBuffer = null;
    String fieldValue = getValue();
    MiscellaneousService.deleteFile(fieldValue);
    setPlaceHolder(placeHolder);
  }

  public void setPlaceHolder(String placeHolder) {
    this.placeHolder = placeHolder;
    boolean isVis = imageImage.isVisible();
    imageImage = UIUtils.getMediumImage(placeHolder);
    avatarLayout.removeAll();
    avatarLayout.add(imageImage, documentIcon, upload, deleteButton);
    imageImage.addClassNames(
        LumoUtility.AlignSelf.CENTER,
        LumoUtility.Border.ALL,
        LumoUtility.BorderColor.CONTRAST_10,
        LumoUtility.BorderRadius.MEDIUM);
    imageImage.setVisible(isVis);
  }

  @Override
  protected String generateModelValue() {

    return null;
  }

  @Override
  protected void setPresentationValue(String image) {
    updateImage(image);
  }

  private void updateImage(String source) {
    String source2 = source == null ? "" : source;

    // if http... is a failsafe in case of mockdata
    if (source2.startsWith("http")) return;

    // images
    if (UIUtils.isFileMemberOf(source2, UIUtils.VALID_IMAGES)) {
      UIUtils.setImage(imageImage, source2);
      return;
    }

    // at this point, only docuemts
    Path path = Paths.get(source2);
    String fName = path.getFileName().toString();
    documentIcon = MiscellaneousService.createFileIcon(fName, 40);
    avatarLayout.removeAll();
    avatarLayout.add(upload, imageImage, documentIcon);
  }
}
