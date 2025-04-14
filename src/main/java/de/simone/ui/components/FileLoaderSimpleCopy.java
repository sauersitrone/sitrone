package de.simone.ui.components;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.customfield.CustomFieldVariant;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;

import de.simone.UIUtils;
import de.simone.backend.MiscellaneousService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileLoaderSimpleCopy extends CustomField<String> {

  private String tenantId = "Default";
  private Upload upload;
  private String filePath;
  private String fileName;
  private FileBuffer fileBuffer;
  private List<String> filesExt;

  public static final int ONE_MB = 1;
  public static final int FOUR_MB = 4;
  public static final int ONE_HUNDRED_MB = 100;

  public static final int IMAGE_SIZE = 2;
  public static final int DOCUMENT_SIZE = 10;
  public static final int VIDE_SIZE = 100;

  public static final long FIELD_HELP = -1;
  public static final long STANDARD_HELP = -2;
  public static final long NO_HELP = -3;

  public FileLoaderSimpleCopy() {
    //
  }

  public FileLoaderSimpleCopy(String fieldName) {
    this(fieldName, ONE_MB, STANDARD_HELP, UIUtils.VALID_IMAGES);
  }

  public FileLoaderSimpleCopy(String fieldName, int sizeInMb) {
    this(fieldName, sizeInMb, STANDARD_HELP, UIUtils.VALID_IMAGES);
  }

  public FileLoaderSimpleCopy(String fieldName, int sizeInMb, String... extensions) {
    this(fieldName, sizeInMb, STANDARD_HELP, extensions);
  }

  public FileLoaderSimpleCopy(String fieldName, int sizeInMb, long helpType, String... extensions) {
    this.filesExt = Arrays.asList(extensions);
    this.filePath = "zitrone.getInstance().publicMediaPath";
    this.upload = UIUtils.getFileUpload(sizeInMb, extensions);
    upload.addSucceededListener(
        event -> {
          fileBuffer = (FileBuffer) upload.getReceiver();
          fileName = event.getFileName();
          MiscellaneousService.saveToFile(
              fileBuffer.getInputStream(),
              fileName
              );
        });
    upload.addFileRejectedListener(
        evt -> {
          setErrorMessage(evt.getErrorMessage());
          setInvalid(true);
        });

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

    add(upload);
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public String getValue() {
    if (fileBuffer != null) {
      String source =
          MiscellaneousService.saveToFile(fileBuffer.getInputStream(), fileName);
      fileBuffer = null;
      setValue(source);
    }
    return super.getValue();
  }

  @Override
  protected String generateModelValue() {
    return "";
  }

  @Override
  protected void setPresentationValue(String image) {
    //
  }
}
