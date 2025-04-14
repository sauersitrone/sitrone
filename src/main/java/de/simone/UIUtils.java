package de.simone;

import com.hilerio.ace.AceEditor;
import com.hilerio.ace.AceMode;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBoxVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datepicker.DatePickerVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePickerVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.select.SelectVariant;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.PropertyChangeListener;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import de.mekaso.vaadin.addon.compani.animation.AnimationBuilder;
import de.mekaso.vaadin.addon.compani.animation.AnimationTypes;
import de.mekaso.vaadin.addon.compani.effect.AttentionSeeker;
import de.mekaso.vaadin.addon.compani.effect.Delay;
import de.mekaso.vaadin.addon.compani.effect.Repeat;
import de.mekaso.vaadin.addon.compani.effect.Speed;
import de.simone.backend.Adult;
import de.simone.backend.AuthenticationResult;
import de.simone.backend.ChatMessage;
import de.simone.backend.Country;
import de.simone.backend.Drug;
import de.simone.backend.Event;
import de.simone.backend.GlobalProperty;
import de.simone.backend.History;
import de.simone.backend.Mailing;
import de.simone.backend.MessagingProvider;
import de.simone.backend.MiscellaneousService;
import de.simone.backend.TAEntity;
import de.simone.backend.Tamagotchi;
import de.simone.backend.User;
import de.simone.components.list.ListItem;
import de.simone.ui.EMailPreview;
import de.simone.ui.TAView;
import de.simone.ui.components.MaterialIcons;
import de.simone.ui.components.TColorPicker;
import de.simone.ui.components.TColorPicker.ColorPreset;
import de.simone.ui.components.TColorPickerVariant;
import de.simone.ui.components.TTimer;
import io.quarkus.logging.Log;
import io.quarkus.mailer.Mail;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class UIUtils {

  public static final String NEW_ACTION = "action.new";
  public static final String EDIT_ACTION = "action.edit";
  public static final String DUPLICATE_ACTION = "action.duplicate";
  public static final String DELETE_ACTION = "action.delete";
  public static final String EXPORT_CSV_ACTION = "action.exportCsv";
  public static final String IMPORT_CSV_ACTION = "action.importCsv";
  public static final String SEARCH_FIELD = "searchField";
  public static final String GRID_CONTEXT_MENU = "gridContextMenu";
  public static final String CARD_CONTEXT_MENU = "cardContextMenu";
  public static final String QUERY_EDITOR = "action.queryEditor";
  public static final String IS_EDIT_ACTION = "isEditAction";
  public static final String AFTER_OF = "afterOf";
  public static final String DATA_VIEW = "dataView";
  public static final String TEST_WIDGET = "action.testWidget";
  public static final String CONFIG_WIDGET = "action.configWidget";
  public static final String AUDIT_REVIEW = "action.auditReview";
  public static final String ENTITY_ID = "entityId";

  public static final String CANCEL_FORM = "action.cancelForm";
  public static final String SAVE_FORM = "action.saveForm";
  public static final String UPDATE_FORM = "action.updateForm";
  public static final String DELETE_FORM = "action.deleteForm";
  public static final String MOCK_FORM = "action.mockForm";
  public static final String OK_FORM = "action.okForm";

  public static final String PAGINATION_LAYOUT = "paginationLayout";

  public static final String COLUMN_VISIBLE = "columnVisible";

  public static final String UID_NAVIGATION = "main.navigation";
  public static final String UID_FOOTER = "main.footer";

  public static final String IMG_PATH = "images/";
  public static final String ICON_PATH = "images/";

  public static final String VARIANT_SUCCESS = "success";
  public static final String VARIANT_ERROR = "error";
  public static final String VARIANT_CONTRAST = "contrast";

  public static final String IMPORT_CHECK = "import.check";
  public static final String IMPORT_COMMIT = "import.commit";

  public static final String EMPTY_IMAGE = IMG_PATH + "empty_imate.png";
  public static final String YOUR_LOGO_HERE = IMG_PATH + "your_logo_here.svg";
  public static final String YOUR_AVATAR_HERE = IMG_PATH + "your_avatar_here.svg";
  public static final String YOUR_SIGNATURE_HERE = IMG_PATH + "your_signature_here.svg";
  public static final String YOUR_CAMPAGNE_LOGO_HERE = IMG_PATH + "your_campagne_logo_here.svg";
  public static final String YOUR_CAMPAGNE_IMAGE_HERE = IMG_PATH + "your_campagne_image_here.svg";
  public static final String YOUR_IMAGE_HERE = IMG_PATH + "your_image_here.svg";
  public static final String YOUR_WIDGET_ICON_HERE = IMG_PATH + "your_widget_icon_here.svg";
  public static final String TWITTER_ICON = ICON_PATH + "twitter.png";
  public static final String FACEBOOK_ICON = ICON_PATH + "facebook.png";
  public static final String PAYPAL_ICON = ICON_PATH + "paypal.png";
  public static final String GOOGLEPAY_ICON = ICON_PATH + "paypal.png";

  public static final String BASE_COLOR = "#FFFFFF";
  public static final String PRIMARY_COLOR = "#3d8ffb";
  public static final String ERROR_COLOR = "#f38e88";

  public static final String LOGGING_FORM_WITH = "25rem";
  public static final String LOGGING_FORM_HEIGHT = "36rem";

  public static final String BOT_NAME = "Hanni";
  public static final String BOT_AVATAR = UIUtils.ICON_PATH + "Hanni.png";

  public static final String[] VALID_IMAGES = {".png", ".jpg", ".jpeg", ".svg"}; // NOSONAR
  public static final String[] VALID_DOCUMENTS = {".pdf", ".docx"}; // NOSONAR
  public static final String[] VALID_VIDEOS = {".mp4", ".mov", ".mov", ".wmv", ".avi"}; // NOSONAR

  public static final String MEDIUM_IMAGE_SIZE = "60px";

  public static final int MOBILE_BREAKPOINT = 400;
  public static final int MOBILE_STRING_TRUNCATE = 40;

  private static final int NOTIFICATION_INFO_DURATION = 2000;
  private static final int NOTIFICATION_ERROR_DURATION = 5000;

  private static DateTimeFormatter dateTimeFormatter =
      DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
  private static NumberFormat uSNumberFormat = NumberFormat.getInstance(Locale.US);
  private static final String INVALID_IMAGE_COMPONENT =
      "Argument 'image' muss be instance of Avatar or Image";

  public static String getSimpleClassName(Class<? extends Component> clazz) {
    String clsName = clazz.getSimpleName();
    if (clsName.endsWith("_Subclass")) clsName = clsName.replace("_Subclass", "");
    return clsName;
  }

  public static String getSimpleClassName(Component component) {
    Class<? extends Component> clazz = component.getClass();
    return getSimpleClassName(clazz);
  }

  /**
   * la-lg - large (circa 20 px) la-xs - extra small la-sm - small la-lx - same as 1x la-1x - same
   * as lx la-2x - 2 times large la-3x - 3 times large la-4x la-5x la-6x la-7x la-8x la-9x la-10x
   * la-fw - ?
   *
   * @param size
   * @param iconClass
   * @return
   */
  public static Span getLaIcon(String size, String iconClass) {
    Span span = new Span();
    span.setClassName(size + " " + iconClass);
    return span;
  }

  public static Span getLaIcon(String iconClass) {
    return getLaIcon("la-lg", iconClass);
  }

  public static void setWidgetBorder(HasStyle hasStyle, boolean add) {
    // alwais
    hasStyle.addClassNames(LumoUtility.BorderRadius.LARGE, LumoUtility.Overflow.HIDDEN);

    if (add) {
      hasStyle.addClassNames(
          LumoUtility.Border.ALL, LumoUtility.BorderColor.CONTRAST_30, LumoUtility.BoxShadow.SMALL);
    } else {
      hasStyle.removeClassNames(
          LumoUtility.Border.ALL, LumoUtility.BorderColor.CONTRAST_30, LumoUtility.BoxShadow.SMALL);
    }
  }

  public static Scroller getScroller(Component component) {
    Scroller scroller = new Scroller(component);
    UIUtils.setGroupStyle(scroller);
    scroller.addClassName(LumoUtility.Border.ALL);
    scroller.setHeightFull();
    return scroller;
  }

  public static void setResponsiveSteps(FormLayout formLayout, int columns) {
    List<ResponsiveStep> steps = new ArrayList<>();

    // Use one column by default
    steps.add(new ResponsiveStep("0", 1));

    if (columns >= 2)
      // Use two columns, if the layout's width exceeds 320px
      steps.add(new ResponsiveStep("320px", 2));

    if (columns >= 3)
      // Use three columns, if the layout's width exceeds 500px
      steps.add(new ResponsiveStep("500px", 3));

    if (columns >= 4) steps.add(new ResponsiveStep("750px", 4));

    formLayout.setResponsiveSteps(steps);
  }

  public static FormLayout getResponsiveFormLayout() {
    List<ResponsiveStep> steps = new ArrayList<>();
    FormLayout formLayout = new FormLayout();

    // desktop: 1920x1040
    // gfunds: 1579x899
    steps.add(new ResponsiveStep("600px", 1));
    steps.add(new ResponsiveStep("900px", 2));
    steps.add(new ResponsiveStep("1240px", 3));
    steps.add(new ResponsiveStep("1440px", 4));

    formLayout.setResponsiveSteps(steps);
    return formLayout;
  }

  public static Scroller getHTMLSpan(String htmlText) {
    Span body = new Span();
    body.getElement().setProperty("innerHTML", htmlText == null ? "" : htmlText);
    Scroller scroller = new Scroller(body);
    return scroller;
  }

  /**
   * return and Span with the innerHTML set to the text passes as argument (if text is html) or a
   * simple text component if text is normal text. if the text is blank or represent a not found
   * constant "!{" retrun a empty component
   *
   * @param text - the content
   * @return the Span
   */
  public static Span getSpan(String text, boolean secondary) {
    Span span = new Span();
    // if explanation is not found or empty, return empty component
    if (StringUtils.isBlank(text) || text.startsWith("!{")) return span;

    // is html?
    if (Pattern.compile("<[^>]*>").matcher(text).find()) {
      span.getElement().setProperty("innerHTML", text);
    } else {
      span.setText(text);
    }
    if (secondary) {
      span.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);
    }
    return span;
  }

  public static Anchor getHomePageButton() {
    return getAnchorButton("aditionalLink.home", "", ButtonVariant.LUMO_PRIMARY);
  }

  public static Anchor getAnchorButton(String bundleId, String href, ButtonVariant variant) {
    String href2 = GlobalProperty.getZitroneHost() + href;
    Button button = new Button(TranslationProvider.getTranslation(bundleId));
    if (variant != null) button.addThemeVariants(variant);

    Anchor homeAnchor = new Anchor(href2, button);
    return homeAnchor;
  }

  public static Span getSpanByKey(String bundleKey, Object... parms) {
    String text = TranslationProvider.getTranslation(bundleKey, parms);
    return getSpan(text, false);
  }

  public static VerticalLayout getWidgetFormArea() {
    VerticalLayout widgetComponent = UIUtils.getCompactVerticalLayout();
    UIUtils.setWidgetBorder(widgetComponent, true);
    return widgetComponent;
  }

  public static void showAuthenticationResultInFields(
      AuthenticationResult result, HasValidation... fields) {
    String message = (new Span()).getTranslation(result.result);

    // special case: password policy contain extra information in the payload
    if (result.result.equals(AuthenticationResult.FAILED_PASSWORD_POLICY))
      message = result.payload.toString();

    for (HasValidation field : fields) {
      field.setErrorMessage(message);
      field.setInvalid(true);
    }
  }

  public static void showEMailPreview(Mail mail) {
    EMailPreview eMailPreview = new EMailPreview(mail);
    eMailPreview.getStyle().set("width", "38rem").set("max-width", "100%");

    Dialog dialog = new Dialog();
    dialog.setHeaderTitle("EMail preview");
    dialog.add(eMailPreview);

    Button cancelButton = new Button("Close", e -> dialog.close());
    dialog.getFooter().add(cancelButton);
    dialog.open();
  }

  /**
   * if the given string is great as {@link #MOBILE_STRING_TRUNCATE} trucate it and append "..." as
   * sufix
   *
   * @param str - the string to trucate
   * @return truncate string with sufix if apply
   */
  public static String truncateMovile(final String str) {
    if (str == null) {
      return null;
    }
    if (str.length() > MOBILE_STRING_TRUNCATE) {
      String result = str.substring(0, MOBILE_STRING_TRUNCATE);
      return result + " ...";
    }
    return str;
  }

  public static String getFormatedAmount(Double amount) {
    return getFormatedAmount(amount, true);
  }

  public static String getFormatedAmount(Long amount) {
    return getFormatedAmount(amount == null ? null : BigDecimal.valueOf(amount), false, false);
  }

  public static String getFormatedAmount(Integer amount) {
    return getFormatedAmount(amount == null ? null : BigDecimal.valueOf(amount), false, false);
  }

  public static String getFormatedAmount(Double amount, boolean withDecimal) {
    return getFormatedAmount(amount == null ? null : BigDecimal.valueOf(amount), withDecimal);
  }

  public static String getFormatedAmount(BigDecimal amount) {
    return getFormatedAmount(amount, true, false);
  }

  public static String getFormatedAmount(BigDecimal amount, boolean replaceZero) {
    return getFormatedAmount(amount, true, replaceZero);
  }

  /**
   * Return a formated strin represtation of the amount argument
   *
   * @param amount - the amount
   * @param withDiecimal - true if 2 decimal placed are requered
   * @param replaceZero - if true and amount = 0, replace the 0 string with "--"
   * @return the formatted amount
   */
  public static String getFormatedAmount(
      BigDecimal amount, boolean withDiecimal, boolean replaceZero) {
    Locale locale = Locale.getDefault();
    NumberFormat numberFormat = NumberFormat.getInstance(locale);
    if (VaadinSession.getCurrent() != null)
      numberFormat = NumberFormat.getInstance(VaadinSession.getCurrent().getLocale());
    numberFormat.setMaximumFractionDigits(withDiecimal ? 2 : 0);
    numberFormat.setMinimumFractionDigits(withDiecimal ? 2 : 0);
    BigDecimal amountToFormat = BigDecimal.valueOf(0);
    if (amount != null) amountToFormat = amount;

    String res = numberFormat.format(amountToFormat);
    if (replaceZero && amountToFormat.doubleValue() == 0) res = "--";

    return res;
  }

  public static String getUSFormatedAmount(Double amount) {
    return getUSFormatedAmount(amount == null ? null : BigDecimal.valueOf(amount));
  }

  public static String getUSFormatedAmount(BigDecimal amount) {
    uSNumberFormat.setMaximumFractionDigits(2);
    uSNumberFormat.setMinimumFractionDigits(2);
    if (amount == null) return uSNumberFormat.format(0);

    return uSNumberFormat.format(amount);
  }

  public static String getFormatedAmount(BigDecimal amount, String currecyCode) {
    // in some situations, the currency is not available
    String sym =
        StringUtils.isBlank(currecyCode) ? "?" : Currency.getInstance(currecyCode).getSymbol();
    return sym + " " + getFormatedAmount(amount);
  }

  /**
   * utility method for custom message password confirmation.
   *
   * @param oldPassword - old password component (can be null)
   * @param newPassword - new password component
   * @param confirmPassword - confirmation password component
   * @return true if all is ok
   */
  public static boolean confirmPasswordFields(
      PasswordField oldPassword, PasswordField newPassword, PasswordField confirmPassword) {
    boolean ok = true;
    if (oldPassword != null && oldPassword.getValue().equals("")) {
      UIUtils.showErrorForField(oldPassword, "invalid.oldPassword");
      ok = false;
    }

    if (newPassword.getValue().equals("")) {
      UIUtils.showErrorForField(newPassword, "invalid.newPassword");
      ok = false;
    }

    if (confirmPassword.getValue().equals("")) {
      UIUtils.showErrorForField(confirmPassword, "invalid.confirmPassword");
      ok = false;
    }

    if (oldPassword != null && oldPassword.getValue().equals(newPassword.getValue())) {
      UIUtils.showErrorForField(oldPassword, "invalid.newAndOld");
      UIUtils.showErrorForField(newPassword, "invalid.newAndOld");
      ok = false;
    }

    if (!confirmPassword.getValue().equals(newPassword.getValue())) {
      UIUtils.showErrorForField(newPassword, "invalid.newAndConfirm");
      UIUtils.showErrorForField(confirmPassword, "invalid.newAndConfirm");
      ok = false;
    }

    return ok;
  }

  public static Image getMediumImage(String source) {
    return getImage(source, MEDIUM_IMAGE_SIZE);
  }

  public static Image getImage(String source, String size) {
    Image image = new Image();
    image.getStyle().set("object-fit", "cover");
    if (source != null) image.setSrc(source);
    if (size != null) {
      image.setWidth(size);
      image.setHeight(size);
    }

    return image;
  }

  public static TColorPicker getTColorPicker(String fieldName, boolean withHelper) {
    TColorPicker TColorPicker = new TColorPicker();
    TColorPicker.setLabel(TColorPicker.getTranslation(fieldName));
    TColorPicker.setId(fieldName);
    ArrayList<ColorPreset> colors = new ArrayList<>();
    colors.add(new ColorPreset("#FFFFFF", "White")); // NOSONAR this is not the same as the constant
    colors.add(new ColorPreset("#C0C0C0", "Silver"));
    colors.add(new ColorPreset("#808080", "Gray"));
    colors.add(new ColorPreset("#000000", "Black"));
    colors.add(new ColorPreset("#FF0000", "Red"));
    colors.add(new ColorPreset("#800000", "Maroon"));
    colors.add(new ColorPreset("#FFFF00", "Yellow"));
    colors.add(new ColorPreset("#808000", "Olive"));
    colors.add(new ColorPreset("#00FF00", "Lime"));
    colors.add(new ColorPreset("#008000", "Green"));
    colors.add(new ColorPreset("#00FFFF", "Aqua"));
    colors.add(new ColorPreset("#008080", "Teal"));
    colors.add(new ColorPreset("#0000FF", "Blue"));
    colors.add(new ColorPreset("#000080", "Navy"));
    colors.add(new ColorPreset("#FF00FF", "Fuchsia"));
    colors.add(new ColorPreset("#800080", "Purple"));
    TColorPicker.setPresets(colors);

    if (withHelper) {
      String helper = TColorPicker.getTranslation(fieldName + ".tt");
      TColorPicker.setHelperText(helper);
      TColorPicker.addThemeVariants(TColorPickerVariant.LUMO_HELPER_ABOVE_FIELD);
    }

    return TColorPicker;
  }

  public static VerticalLayout getCompactVerticalLayout(Component... components) {
    VerticalLayout verticalLayout = new VerticalLayout(components);
    setCompatStyle(verticalLayout);
    return verticalLayout;
  }

  public static void setCompatStyle(VerticalLayout verticalLayout) {
    verticalLayout.setPadding(false);
    verticalLayout.setSpacing(false);
    verticalLayout.setMargin(false);
    verticalLayout.setWidthFull();
  }

  public static PasswordField getPasswordField(String fieldName) {
    return getPasswordField(fieldName, false);
  }

  public static PasswordField getPasswordField(String fieldName, boolean withHelper) {
    PasswordField field = new PasswordField();
    field.setLabel(field.getTranslation(fieldName));
    field.setRevealButtonVisible(false);
    if (withHelper) {
      String helper = field.getTranslation(fieldName + ".tt");
      field.setHelperText(helper);
      field.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    return field;
  }

  public static boolean showResponseInFields(
      Response response, String messageId, HasValidation... fields) {
    Map<String, Object> map = (Map) response.getEntity();
    if (messageId.equals(map.get("messageId"))) {
      return showResponseInFields(response, fields);
    }

    return true;
  }

  /**
   * show the response message (any) on the error message area of the fiels pased as argument. if is
   * there no error, this method return silenty
   *
   * @param response - the response
   * @param fields - the fiels
   * @return true for no error, false there was an error
   */
  public static boolean showResponseInFields(Response response, HasValidation... fields) {
    boolean isOk = response.getStatus() < 400;
    if (isOk) return true;
    String message = ((Map<?, ?>) response.getEntity()).get("message").toString();
    for (HasValidation field : fields) {
      field.setErrorMessage(message);
      field.setInvalid(true);
    }
    return isOk;
  }

  public static void showErrorForField(HasValidation textField, String bundleKey, Object... parms) {
    textField.setErrorMessage(new Span().getTranslation(bundleKey, parms));
    textField.setInvalid(true);
  }

  public static Icon createPrimaryIcon(VaadinIcon icon) {
    Icon i = new Icon(icon);
    i.addClassName(LumoUtility.TextColor.PRIMARY);
    return i;
  }

  public static Icon createSecondaryIcon(VaadinIcon icon) {
    Icon i = new Icon(icon);
    i.addClassName(LumoUtility.TextColor.SECONDARY);
    return i;
  }

  public static Icon createTertiaryIcon(VaadinIcon icon) {
    Icon i = new Icon(icon);
    i.addClassName(LumoUtility.TextColor.TERTIARY);
    return i;
  }

  public static Icon createDisabledIcon(VaadinIcon icon) {
    Icon i = new Icon(icon);
    i.addClassName(LumoUtility.TextColor.DISABLED);
    return i;
  }

  public static Icon createSuccessIcon(VaadinIcon icon) {
    Icon i = new Icon(icon);
    i.addClassName(LumoUtility.TextColor.SUCCESS);
    return i;
  }

  public static Icon createErrorIcon(VaadinIcon icon) {
    Icon i = new Icon(icon);
    i.addClassName(LumoUtility.TextColor.ERROR);
    return i;
  }

  public static Icon createBadgeIcon(VaadinIcon vaadinIcon) {
    Icon icon = vaadinIcon.create();
    icon.addClassNames(LumoUtility.Padding.XSMALL);
    return icon;
  }

  public static Span getOnlyTrueBooleanBadge(boolean b) {
    Span span = new Span();
    if (b) span.getElement().getThemeList().add("badge");
    return span;
  }

  public static Span getBooleanBadge(boolean b) {
    Span span = new Span();
    span.getElement().getThemeList().add("badge");
    span.getElement().getThemeList().add(b ? VARIANT_SUCCESS : VARIANT_CONTRAST);
    return span;
  }

  public static Span getBooleanBadge(boolean b, boolean withText, boolean withIcon) {
    String group = withText ? "boolean.enabled" : null;
    return getBooleanBadge(b, group, withIcon);
  }

  public static Span getIsLiveBadge(boolean b) {
    return getBooleanBadge(b, "boolean.isLive", false);
  }

  public static Span getBooleanBadge(boolean b, String group, boolean withIcon) {
    Span span = new Span();
    span.getElement().getThemeList().add("badge");
    if (withIcon) {
      Icon icon = b ? createBadgeIcon(VaadinIcon.CHECK) : createBadgeIcon(VaadinIcon.MINUS);
      span.add(icon);
    }
    if (group != null) {
      span.add(new Span(TranslationProvider.getString(group, "" + b)));
    }
    span.getElement().getThemeList().add(b ? VARIANT_SUCCESS : VARIANT_CONTRAST);
    return span;
  }

  public static Span getBadge(String bundleGroup, String bundleId, String variant) {
    return getBadge(bundleGroup, bundleId, variant, null);
  }

  public static Span getBadge(String bundleGroup, String bundleId, String variant, Icon icon) {
    Span span = new Span();
    span.getElement().getThemeList().add("badge");
    if (icon != null) {
      span.add(icon);
    }

    span.add(
        bundleId == null
            ? new Span()
            : new Span(TranslationProvider.getString(bundleGroup, bundleId)));

    if (variant != null) span.getElement().getThemeList().add(variant);
    return span;
  }

  public static Span getIndicatorBadge(Double value) {
    Span span = new Span();
    span.getElement().getThemeList().add("badge");
    Icon icon = null;
    String variant = null;
    if (value == 0) {
      icon = createBadgeIcon(VaadinIcon.MINUS);
    }
    if (value > 0) {
      icon = createBadgeIcon(VaadinIcon.ARROW_UP);
      variant = VARIANT_SUCCESS;
    }
    if (value < 0) {
      icon = createBadgeIcon(VaadinIcon.ARROW_DOWN);
      variant = VARIANT_ERROR;
    }

    if (icon != null) span.add(icon);

    span.add(new Span(getFormatedAmount(value)));

    if (variant != null) span.getElement().getThemeList().add(variant);
    return span;
  }

  /**
   * create and return a standar timer
   *
   * @param listener - {@link PropertyChangeListener} of the timer
   * @param seconds - notification duration interval
   * @return the timer
   */
  public static TTimer getTimer(PropertyChangeListener listener, int seconds) {
    TTimer timer = new TTimer(3600 * 24);
    timer.setFractions(false);
    timer.setVisible(false);
    timer.setCountUp(true);
    timer.addCurrentTimeChangeListener(listener, seconds, TimeUnit.SECONDS);
    return timer;
  }

  public static ComponentRenderer<Span, User> getUserStatusBadge() {
    ComponentRenderer<Span, User> badgeRenderer =
        new ComponentRenderer<>(
            acc -> {
              String constantGroup = "user.status";
              Span span = new Span();
              if (User.VERIFIED.equals(acc.status))
                span = getBadge(constantGroup, acc.status, VARIANT_SUCCESS);
              if (User.INCOMPLETE.equals(acc.status))
                span = getBadge(constantGroup, acc.status, VARIANT_CONTRAST);
              if (User.UNVERIFIED.equals(acc.status))
                span = getBadge(constantGroup, acc.status, null);

              return span;
            });
    return badgeRenderer;
  }

  public static Span getEventStatusBadge(Event event) {
    String constantGroup = "event.status";
    Span span = new Span();
    if (Event.ATTENDED.equals(event.status))
      span = getBadge(constantGroup, event.status, VARIANT_SUCCESS);
    if (Event.MISSED.equals(event.status))
      span = getBadge(constantGroup, event.status, VARIANT_CONTRAST);
    if (Event.WAITING.equals(event.status)) span = getBadge(constantGroup, event.status, null);

    return span;
  }

  public static Span getSocialMediaIcon(String laIcon, String text, String color) {
    Span label = new Span(text);
    Span span = new Span(UIUtils.getLaIcon(laIcon), label);
    span.getStyle().set("color", color);
    return span;
  }

  public static Dialog getDialog(
      String titleId,
      String messageId,
      List<Button> acceptButtons,
      String cancelTextId,
      Component... components) {
    Dialog dialog = new Dialog();

    if (titleId != null) dialog.setHeaderTitle(dialog.getTranslation(titleId));

    if (components != null) {
      VerticalLayout dialogLayout = UIUtils.getCompactVerticalLayout();
      if (messageId != null) dialogLayout.add(UIUtils.getSecondarySmallLabel(messageId));

      dialogLayout.add(components);
      dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
      dialogLayout.setSizeFull();
      dialog.add(dialogLayout);
    }

    if (cancelTextId != null) {
      Button cancelButton = UIUtils.getButton(cancelTextId, null);
      cancelButton.addClickListener(e -> dialog.close());
      dialog.getFooter().add(cancelButton);
    }
    if (!acceptButtons.isEmpty()) dialog.getFooter().add(acceptButtons.toArray(new Button[0]));
    return dialog;
  }

  public static ConfirmDialog getDeleteDialog(Class<?> entitiyClass) {
    String elementName = TranslationProvider.getTranslation(entitiyClass.getSimpleName());
    String title = TranslationProvider.getTranslation("ConfirmDialog.delete.title", elementName);
    String message =
        TranslationProvider.getTranslation("ConfirmDialog.delete.message", elementName);
    ConfirmDialog dialog =
        getConfirmDialog(
            title, message, "ConfirmDialog.delete.cancel", "ConfirmDialog.delete.accept");
    dialog.setConfirmButtonTheme("error primary");
    return dialog;
  }

  public static ConfirmDialog getConfirmDialog(
      String title, String message, String cancelMsgId, String confirmMsgId) {
    ConfirmDialog dialog = new ConfirmDialog();
    dialog.setHeader(title);
    dialog.setText(message);
    dialog.setCancelable(true);
    dialog.setCancelText(dialog.getTranslation(cancelMsgId));
    dialog.setConfirmText(dialog.getTranslation(confirmMsgId));

    return dialog;
  }

  public static <T> Grid<T> getGrid(Class<T> clazz) {
    Grid<T> grid = new Grid<>(clazz, false);
    grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
    return grid;
  }

  public static EmailField getEmailField(String fieldName) {
    return getEmailField(fieldName, false, false);
  }

  public static EmailField getEmailField(String fieldName, boolean withHelper, boolean readOnly) {
    EmailField emailField = new EmailField();
    emailField.setLabel(emailField.getTranslation(fieldName));
    emailField.setRequiredIndicatorVisible(true);
    emailField.getElement().setAttribute("name", "email");
    if (withHelper) {
      String helper = emailField.getTranslation(fieldName + ".tt");
      emailField.setHelperText(helper);
      emailField.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    emailField.setReadOnly(readOnly);
    emailField.setId(fieldName);
    return emailField;
  }

  public static RadioButtonGroup<String> getToggleButtonGroup(
      String fieldName, String group, boolean withHelper) {
    List<String> keys = TranslationProvider.getKeys(group);
    RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
    radioButtonGroup.setItems(keys);

    radioButtonGroup.setRenderer(
        new ComponentRenderer<>(
            item -> {
              Span span = new Span(item);
              span.addClassNames(Padding.Horizontal.SMALL);
              return span;
            }));

    if (withHelper) {
      String helper = radioButtonGroup.getTranslation(fieldName + ".tt");
      radioButtonGroup.setHelperText(helper);
      radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    return radioButtonGroup;
  }

  public static TextField getTextField(String fieldName) {
    return getTextField(fieldName, false, false);
  }

  public static void setGroupStyle(HasStyle hasStyle) {
    hasStyle.addClassNames(
        LumoUtility.Border.ALL,
        LumoUtility.BorderColor.CONTRAST_20,
        LumoUtility.Background.BASE,
        LumoUtility.BorderRadius.SMALL);
  }

  public static MultiSelectListBox<String> getMultiSelectListBox(String fieldName) {
    MultiSelectListBox<String> listBox = new MultiSelectListBox<>();
    listBox.setId(fieldName);
    listBox.addClassNames(LumoUtility.Border.ALL, LumoUtility.BorderColor.CONTRAST_20);
    return listBox;
  }

  public static MultiSelectListBox<BigDecimal> getMultiSelectListBoxDecimal(String fieldName) {
    MultiSelectListBox<BigDecimal> listBox = new MultiSelectListBox<>();
    listBox.setId(fieldName);
    listBox.addClassNames(LumoUtility.Border.ALL, LumoUtility.BorderColor.CONTRAST_20);
    return listBox;
  }

  public static VerticalLayout getWrapForMultiSelectListBox(MultiSelectListBox<?> listBox) {
    String fieldName = listBox.getId().orElse("no id");
    ListItem item = new ListItem(fieldName, fieldName + ".tt");
    listBox.setWidthFull();
    VerticalLayout layout = new VerticalLayout(item, listBox);
    layout.setSpacing(false);
    layout.setPadding(false);
    layout.addClassName(LumoUtility.Margin.Top.MEDIUM);
    return layout;
  }

  public static VerticalLayout getWrapForGrid(Grid<?> grid) {
    String fieldName = grid.getId().orElse("null");
    ListItem item = getPrimarySecondaryListItem(fieldName, fieldName + ".tt");
    grid.setWidthFull();
    VerticalLayout layout = getCompactVerticalLayout(item, grid);
    layout.addClassName(LumoUtility.Margin.Top.MEDIUM);
    return layout;
  }

  public static VerticalLayout getVerticalLayout(Component... children) {
    VerticalLayout layout = children == null ? new VerticalLayout() : new VerticalLayout(children);
    layout.setSpacing(false);
    layout.setPadding(false);
    return layout;
  }

  public static HorizontalLayout getHorizontalLayout2(Component... children) {
    HorizontalLayout layout =
        children == null ? new HorizontalLayout() : new HorizontalLayout(children);
    layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
    layout.setWidthFull();
    layout.setPadding(false);
    return layout;
  }

  public static TextField getTextField(String fieldName, boolean withHelper, boolean readOnly) {
    TextField field = new TextField();
    field.setLabel(field.getTranslation(fieldName));
    if (withHelper) {
      String helper = field.getTranslation(fieldName + ".tt");
      field.setHelperText(helper);
      field.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    field.setReadOnly(readOnly);
    field.setId(fieldName);
    return field;
  }

  public static BigDecimalField getBigDecimalField(String fieldName) {
    return getBigDecimalField(fieldName, false, false);
  }

  public static BigDecimalField getBigDecimalField(
      String fieldName, boolean withHelper, boolean readOnly) {
    BigDecimalField big = new BigDecimalField();
    big.setLabel(big.getTranslation(fieldName));
    big.setId(fieldName);
    if (withHelper) {
      String helper = big.getTranslation(fieldName + ".tt");
      big.setHelperText(helper);
      big.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    big.setReadOnly(readOnly);
    big.setWidthFull();
    return big;
  }

  public static NumberField getNumberField(String fieldName, boolean withHelper, boolean readOnly) {
    NumberField numberF = new NumberField();
    numberF.setLabel(numberF.getTranslation(fieldName));
    numberF.setId(fieldName);
    if (withHelper) {
      String helper = numberF.getTranslation(fieldName + ".tt");
      numberF.setHelperText(helper);
      numberF.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    numberF.setReadOnly(readOnly);
    numberF.setWidthFull();
    return numberF;
  }

  public static DatePicker getDatePicker(String fieldName, boolean withHelper, boolean readOnly) {
    DatePicker picker = new DatePicker();
    picker.setLabel(picker.getTranslation(fieldName));
    if (withHelper) {
      String helper = picker.getTranslation(fieldName + ".tt");
      picker.setHelperText(helper);
      picker.addThemeVariants(DatePickerVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    picker.setReadOnly(readOnly);
    return picker;
  }

  public static DateTimePicker getDateTimePicker(String fieldName) {
    return getDateTimePicker(fieldName, false, false);
  }

  public static DateTimePicker getDateTimePicker(
      String fieldName, boolean withHelper, boolean readOnly) {
    DateTimePicker picker = new DateTimePicker();
    picker.setLabel(picker.getTranslation(fieldName));
    if (withHelper) {
      String helper = picker.getTranslation(fieldName + ".tt");
      picker.setHelperText(helper);
      picker.addThemeVariants(DateTimePickerVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    picker.setReadOnly(readOnly);
    return picker;
  }

  public static TextArea getTextArea(String fieldName) {
    return getTextArea(fieldName, false, false);
  }

  public static void setTextAreaHeight(TextArea textArea, int lines) {
    int lineHeight = 10;
    if (lines < 1) return;
    textArea.setWidthFull();
    textArea.setHeight((lines * lineHeight) + "px");
    textArea.setMaxHeight((lines * lineHeight) + "px");
  }

  public static TextArea getTextArea(String fieldName, boolean withHelper, boolean readOnly) {
    TextArea area = new TextArea();
    area.setLabel(area.getTranslation(fieldName));
    area.setId(fieldName);
    if (withHelper) {
      String helper = area.getTranslation(fieldName + ".tt");
      area.setHelperText(helper);
      area.addThemeVariants(TextAreaVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    area.setReadOnly(readOnly);

    return area;
  }

  /**
   * return the icon asociated with a toolbar component. use this method to configure icons to pass
   * as parameters in {@link #getToolBar(TAView, Icon...)} method
   *
   * @param icon - the icon
   * @param id - id of the component
   * @param after - the toolbar action name to put the action component associated with this icon
   * @return the icon
   */
  public static Icon getAfterIcon(Icon icon, String id, String after) {
    icon.setId(id);
    icon.getElement().setProperty(AFTER_OF, after);
    return icon;
  }

  public static SvgIcon getAfterIcon(SvgIcon icon, String id, String after) {
    icon.setId(id);
    icon.getElement().setProperty(AFTER_OF, after);
    return icon;
  }

  public static List<Component> getToolBar(
      TAView<? extends TAEntity> listener, Icon... afterComponets) {
    List<Component> actionsComponent = new ArrayList<>();
    List<Icon> afterCmps = Arrays.asList(afterComponets);

    TextField search = new TextField();
    Button clear = new Button(VaadinIcon.CLOSE.create());
    clear.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
    clear.addClickListener(e -> search.clear());
    search.setPrefixComponent(VaadinIcon.SEARCH.create());
    search.setPlaceholder(search.getTranslation("search.textfield"));
    search.setClearButtonVisible(true);
    search.setValueChangeMode(ValueChangeMode.EAGER);
    search.setId(SEARCH_FIELD);
    search.getElement().setProperty(IS_EDIT_ACTION, false);
    actionsComponent.add(search);

    Button add = new Button(LineAwesomeIcon.PLUS_SOLID.create());
    add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    add.setId(NEW_ACTION);
    add.setTooltipText(TranslationProvider.getTranslation(NEW_ACTION));
    add.addClickListener(listener);
    add.getElement().setProperty(IS_EDIT_ACTION, false);
    actionsComponent.add(add);

    // component marked as afterOF = NEW_ACTION
    for (Icon icon : afterCmps) {
      if (icon.getElement().getProperty(AFTER_OF, "").equals(NEW_ACTION)) {
        Button button = new Button(icon, listener);
        String id = icon.getId().orElse("null");
        button.setId(id);
        button.setTooltipText(TranslationProvider.getTranslation(id));
        button.getElement().setProperty(IS_EDIT_ACTION, false);
        actionsComponent.add(button);
      }
    }

    Button edit = new Button(MaterialIcons.EDIT.create());
    edit.setId(EDIT_ACTION);
    edit.setTooltipText(TranslationProvider.getTranslation(EDIT_ACTION));
    edit.setEnabled(false);
    edit.getElement().setProperty(IS_EDIT_ACTION, true);
    edit.addClickListener(listener);
    actionsComponent.add(edit);

    // component marked as afterOF = EDIT_ACTION
    for (Icon icon : afterCmps) {
      if (icon.getElement().getProperty(AFTER_OF, "").equals(EDIT_ACTION)) {
        Button button = new Button(icon, listener);
        String id = icon.getId().orElse("null");
        button.setId(id);
        button.setTooltipText(TranslationProvider.getTranslation(id));
        button.setEnabled(false);
        button.getElement().setProperty(IS_EDIT_ACTION, true);
        actionsComponent.add(button);
      }
    }

    Button duplicate = new Button(LineAwesomeIcon.COPY.create());
    duplicate.setId(DUPLICATE_ACTION);
    duplicate.setTooltipText(TranslationProvider.getTranslation(DUPLICATE_ACTION));
    duplicate.setEnabled(false);
    duplicate.getElement().setProperty(IS_EDIT_ACTION, true);
    duplicate.addClickListener(listener);
    actionsComponent.add(duplicate);

    Button delete = new Button(LineAwesomeIcon.TRASH_ALT.create());
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    delete.setId(DELETE_ACTION);
    delete.setTooltipText(TranslationProvider.getTranslation(DELETE_ACTION));
    delete.setEnabled(false);
    delete.getElement().setProperty(IS_EDIT_ACTION, true);
    delete.addClickListener(listener);
    actionsComponent.add(delete);

    // selection list for grid component
    listener.grid.addSelectionListener(
        evt -> {
          boolean isPresent = evt.getFirstSelectedItem().isPresent();
          actionsComponent.forEach(
              mi -> {
                if (mi.getElement().getProperty(IS_EDIT_ACTION, false))
                  ((Button) mi).setEnabled(isPresent);
              });
        });

    return actionsComponent;
  }

  public static List<Component> getElementToolBar(
      TAView<? extends TAEntity> listener, Icon... afterComponets) {
    List<Component> actionsComponent = new ArrayList<>();
    List<Icon> afterCmps = Arrays.asList(afterComponets);

    Button edit = new Button(MaterialIcons.EDIT.create());
    edit.setId(EDIT_ACTION);
    edit.setEnabled(false);
    edit.getElement().setProperty(IS_EDIT_ACTION, true);
    edit.addClickListener(listener);
    actionsComponent.add(edit);

    // component marked as afterOF = EDIT_ACTION
    for (Icon icon : afterCmps) {
      if (icon.getElement().getProperty(AFTER_OF, "").equals(EDIT_ACTION)) {
        Button button = new Button(icon, listener);
        button.setId(icon.getId().orElse("null"));
        button.setEnabled(false);
        button.getElement().setProperty(IS_EDIT_ACTION, true);
        actionsComponent.add(button);
      }
    }

    Button copy = new Button(MaterialIcons.COPY_ALL.create());
    copy.setId(DUPLICATE_ACTION);
    copy.setEnabled(false);
    copy.getElement().setProperty(IS_EDIT_ACTION, true);
    copy.addClickListener(listener);
    actionsComponent.add(copy);

    Button delete = new Button(MaterialIcons.DELETE.create());
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    delete.setId(DELETE_ACTION);
    delete.setEnabled(false);
    delete.getElement().setProperty(IS_EDIT_ACTION, true);
    delete.addClickListener(listener);
    actionsComponent.add(delete);

    return actionsComponent;
  }

  public static Checkbox getCheckbox(String fieldName) {
    return getCheckbox(fieldName, false);
  }

  public static Checkbox getCheckbox(String fieldName, boolean readOnly) {
    Checkbox checkbox = new Checkbox();
    checkbox.setLabel(checkbox.getTranslation(fieldName));
    checkbox.setId(fieldName);
    checkbox.setReadOnly(readOnly);
    return checkbox;
  }

  public static IntegerField getStepperIntegerField(
      String fieldName, boolean withHelper, int min, int max, int step) {
    IntegerField field = new IntegerField();
    field.setLabel(field.getTranslation(fieldName));
    field.setMin(min);
    field.setMax(max);
    field.setStep(step);
    if (withHelper) {
      String helper = field.getTranslation(fieldName + ".tt");
      field.setHelperText(helper);
      field.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    return field;
  }

  public static IntegerField getIntegerField(String fieldName) {
    return getIntegerField(fieldName, null, false, false);
  }

  public static IntegerField getIntegerField(
      String fieldName, boolean withHelper, boolean readOnly) {
    return getIntegerField(fieldName, null, withHelper, readOnly);
  }

  public static IntegerField getIntegerField(
      String fieldName, String withErrorMessage, boolean withHelper, boolean readOnly) {
    IntegerField field = new IntegerField();
    field.setLabel(field.getTranslation(fieldName));

    if (withErrorMessage != null) field.setErrorMessage(field.getTranslation(withErrorMessage));

    if (withHelper) {
      String helper = field.getTranslation(fieldName + ".tt");
      field.setHelperText(helper);
      field.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    field.setReadOnly(readOnly);
    return field;
  }

  public static Component getHelperComponent(String fieldName, Object... params) {
    String text = TranslationProvider.getTranslation(fieldName + ".tt", params);
    return getHelperComponent(text, false);
  }

  public static Component getHelperComponent(String text, boolean shortHelp) {
    if (shortHelp) {
      int punt = text.indexOf(".");
      // if the tooltip sentence dont contain "." or the point is an end of the
      // sentence, icon
      // will
      // be no visible
      boolean vis = punt > 0 && punt + 1 < text.length();
      String helper = vis ? text.split("[.]")[0] : text;
      Icon icon = VaadinIcon.QUESTION_CIRCLE_O.create();
      icon.addClassName(LumoUtility.IconSize.SMALL);
      icon.getElement().setProperty("title", text);
      icon.setVisible(vis);
      Div div = new Div(new Text(helper + "  "), icon);
      return div;
    } else {
      return new Div(new Text(text));
    }
  }

  public static CheckboxGroup<String> getCheckboxGroup(String fieldName) {
    CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
    checkboxGroup.setLabel(checkboxGroup.getTranslation(fieldName + ".tt"));
    checkboxGroup.setItems(checkboxGroup.getTranslation(fieldName));
    return checkboxGroup;
  }

  public static H4 getH4Title(String bundleKey) {
    H4 h4 = new H4();
    h4.setText(h4.getTranslation(bundleKey));
    h4.addClassNames(LumoUtility.Border.BOTTOM, LumoUtility.TextColor.SECONDARY);
    return h4;
  }

  public static Span getSecondaryMediumLabel(String fieldName, Object... params) {
    Span label = new Span();
    label.setText(label.getTranslation(fieldName, params));
    label.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.MEDIUM);
    return label;
  }

  public static Span getSecondarySmallLabel(String fieldName, Object... params) {
    Span label = new Span();
    label.setText(label.getTranslation(fieldName, params));
    label.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.SMALL);
    return label;
  }

  /**
   * return a Help Styled Label
   *
   * @param ttkey - the key (with .tt) sufix
   * @param params - the parameters
   * @return the label
   */
  public static Span getHelpLabel(String ttkey, Object... params) {
    Span help = new Span();
    String helper = help.getTranslation(ttkey, params);
    help.setText(helper);
    help.addClassNames(
        LumoUtility.FontSize.XSMALL,
        LumoUtility.TextColor.SECONDARY,
        LumoUtility.LineHeight.XSMALL);
    return help;
  }

  public static VerticalLayout getLabelAndHelp(
      String labelKey, boolean withHelper, Object... params) {
    Span label = new Span();
    if (labelKey != null) {
      label.setText(label.getTranslation(labelKey, params));
      label.addClassNames(
          LumoUtility.FontSize.SMALL,
          LumoUtility.TextColor.SECONDARY,
          LumoUtility.FontWeight.SEMIBOLD);
    }

    Span help = new Span();
    if (withHelper) {
      help = getHelpLabel(labelKey + ".tt", params);
    }

    return getCompactVerticalLayout(label, help);
  }

  public static Div getOneLineComponentRenderer(String label, String text) {
    Span label2 = new Span(label + ": ");
    Span text2 = new Span(text);
    text2.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.SMALL);
    return new Div(label2, text2);
  }

  public static VerticalLayout getWraperForImageUpload(Component image, Upload upload) {
    return getWraperForImageUpload(image, null, null, upload, 20);
  }

  public static boolean isImage(String value) {
    boolean ok = false;
    for (String ext : VALID_IMAGES) {
      if (value.endsWith(ext.trim())) ok = true;
    }
    return ok;
  }

  public static VerticalLayout getWraperForImageUpload(
      Component image, String imageWith, String imageHeight, Upload upload, int sizeInKb) {

    if (!(image instanceof Image || image instanceof Avatar))
      throw new IllegalArgumentException(INVALID_IMAGE_COMPONENT);

    ListItem item =
        getListItem(
            "uploadFile.wraper.title",
            "uploadFile.wraper.message",
            null,
            sizeInKb + " Kb",
            VALID_IMAGES);
    ((HasSize) image).setWidth(imageWith == null ? "100px" : imageWith);
    ((HasSize) image).setHeight(imageHeight == null ? "100px" : imageHeight);
    ((HasStyle) image).addClassName(LumoUtility.AlignSelf.CENTER);

    upload.setWidthFull();

    HorizontalLayout lHorizontal = new HorizontalLayout(image, upload);
    lHorizontal.setPadding(false);
    lHorizontal.setSpacing(true);
    lHorizontal.setWidthFull();

    VerticalLayout lVertical = new VerticalLayout(item, lHorizontal);
    lVertical.setPadding(false);
    lVertical.setSpacing(true);
    return lVertical;
  }

  public static Upload getImageUpload(Component image, TextField textField) {
    return getImageUpload(image, textField, 20);
  }

  public static Upload getFileUpload(int sizeInMb, String... fileTypes) {
    MemoryBuffer buffer2 = new MemoryBuffer();
    Upload upload = new Upload(buffer2);
    upload.setAutoUpload(true);
    upload.setAcceptedFileTypes(fileTypes);
    upload.setMaxFileSize(sizeInMb * 1024 * 1024);
    upload.setMaxFiles(1);
    return upload;
  }

  public static Upload getImageUpload(Component image, TextField textField, int sizeInMb) {
    if (!(image instanceof Image || image instanceof Avatar))
      throw new IllegalArgumentException(INVALID_IMAGE_COMPONENT);

    MemoryBuffer buffer2 = new MemoryBuffer();
    Upload upload = new Upload(buffer2);
    upload.setI18n(new UploadI18N());
    upload.setAutoUpload(true);
    upload.setAcceptedFileTypes("image/png", "image/jpg", "image/jpeg", "image/svg");
    upload.setMaxFileSize(sizeInMb * 1024 * 1024);
    upload.setMaxFiles(1);
    upload.addFileRejectedListener(
        evt ->
            UIUtils.showNotification(
                VaadinIcon.WARNING,
                LumoUtility.TextColor.ERROR,
                "Error",
                evt.getErrorMessage(),
                0));

    upload.addSucceededListener(
        event -> {
          MemoryBuffer buffer = (MemoryBuffer) upload.getReceiver();
          MiscellaneousService service = new MiscellaneousService();
          String fileName = event.getFileName();
          try {
            String source = service.saveImageToFile(buffer.getInputStream(), fileName);
            textField.setValue(source);
            UIUtils.setImage(image, source);
          } catch (Exception e) {
            Log.error("", e);
            UIUtils.showErrorNotification("uploadFile.error");
          }
        });

    return upload;
  }

  public static VerticalLayout getTitleH(String bundleKey, boolean withHelper) {
    H4 title = new H4();
    title.setText(title.getTranslation(bundleKey));
    title.addClassNames(LumoUtility.Margin.Bottom.XSMALL, Margin.Top.MEDIUM);

    // save space for help line
    if (!withHelper) title.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

    VerticalLayout layout = getCompactVerticalLayout(title);
    // layout.addClassNames(LumoUtility.Border.BOTTOM, LumoUtility.BorderColor.CONTRAST_20);

    if (withHelper) {
      Span text = new Span();
      text.setText(text.getTranslation(bundleKey + ".tt"));
      text.addClassNames(
          LumoUtility.TextColor.SECONDARY,
          LumoUtility.FontSize.SMALL,
          LumoUtility.Margin.Left.MEDIUM);
      layout.add(text);
    }
    return layout;
  }

  public static VerticalLayout getTitle(String bundleKey, boolean withHelper) {
    Span title = new Span();
    title.setText(title.getTranslation(bundleKey));
    title.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.LARGE);

    Span text = new Span();
    text.setText(text.getTranslation(bundleKey + ".tt"));
    text.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.FontSize.XSMALL);
    text.setVisible(withHelper);

    VerticalLayout layout = new VerticalLayout(title, text);
    layout.setPadding(false);
    layout.setSpacing(false);
    layout.addClassName(LumoUtility.Border.BOTTOM);
    return layout;
  }

  public static Button getButton(String bundleKey, ButtonVariant variant) {
    Button button = new Button();
    if (bundleKey != null) button.setText(button.getTranslation(bundleKey));
    if (variant != null) button.addThemeVariants(variant);
    return button;
  }

  public static MultiSelectComboBox<String> getMultiSelectComboBox(
      String group, String fieldName, boolean wihtHelper) {
    MultiSelectComboBox<String> comboBox = new MultiSelectComboBox<>();
    List<String> elements = TranslationProvider.getKeys(group);
    comboBox.setItems(elements);
    comboBox.setItemLabelGenerator(key -> TranslationProvider.getString(group, key));
    comboBox.setLabel(comboBox.getTranslation(fieldName));
    if (wihtHelper) {
      comboBox.setHelperComponent(getHelperComponent(fieldName));
      comboBox.addThemeVariants(MultiSelectComboBoxVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    return comboBox;
  }

  public static <T> MultiSelectComboBox<T> getMultiSelectComboBoxTemplate(
      String fieldName, ItemLabelGenerator<T> generator) {
    return getMultiSelectComboBoxTemplate(fieldName, false, generator);
  }

  public static <T> MultiSelectComboBox<T> getMultiSelectComboBoxTemplate(
      String fieldName, boolean wihtHelper, ItemLabelGenerator<T> generator) {
    MultiSelectComboBox<T> comboBox = getMultiSelectComboBoxTemplate(fieldName, wihtHelper, false);
    comboBox.setItemLabelGenerator(generator);
    return comboBox;
  }

  public static <T> MultiSelectComboBox<T> getMultiSelectComboBoxTemplate(
      String fieldName, boolean wihtHelper, boolean readOnly) {
    MultiSelectComboBox<T> comboBox = new MultiSelectComboBox<>();
    comboBox.setLabel(comboBox.getTranslation(fieldName));
    comboBox.setId(fieldName);
    if (wihtHelper) {
      comboBox.setHelperComponent(getHelperComponent(fieldName));
      comboBox.addThemeVariants(MultiSelectComboBoxVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    comboBox.setReadOnly(readOnly);
    return comboBox;
  }

  public static RadioButtonGroup<String> getRadioButtonGroup(String group, String fieldName) {
    return getRadioButtonGroup(group, fieldName, false, false);
  }

  public static RadioButtonGroup<String> getRadioButtonGroup(
      String group, String fieldName, boolean withHelper, boolean readOnly) {
    List<String> elements = TranslationProvider.getKeys(group);
    RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
    radioGroup.setLabel(radioGroup.getTranslation(fieldName));
    if (withHelper) {
      String helper = radioGroup.getTranslation(fieldName + ".tt");
      radioGroup.setHelperText(helper);
      radioGroup.addThemeVariants(RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    radioGroup.setRenderer(
        new ComponentRenderer<>(
            item -> {
              Text text = new Text(TranslationProvider.getString(group, item));
              return text;
            }));
    radioGroup.setItems(elements);
    radioGroup.setValue(elements.get(0));
    radioGroup.setReadOnly(readOnly);
    return radioGroup;
  }

  public static <T> Select<T> getSelectTemplate(
      String fieldName, boolean withHelper, String emptySelection) {
    Select<T> select = new Select<>();
    setSelectProperties(select, fieldName, withHelper, false);
    if (emptySelection != null) {
      select.setEmptySelectionAllowed(true);
      select.setEmptySelectionCaption(select.getTranslation(emptySelection));
    }
    return select;
  }

  public static Select<MessagingProvider> getMessagingProviderSelect(
      String fieldName, boolean withHelper, String emptySelection) {
    Select<MessagingProvider> select = getSelectTemplate(fieldName, withHelper, emptySelection);
    select.setRenderer(
        new ComponentRenderer<>(te -> getSmallImageRender(te.getIcon(), te.getName(), null)));
    return select;
  }

  public static Select<Mailing> getMailingSelect(
      String fieldName, boolean withHelper, String emptySelection) {
    Select<Mailing> select = getSelectTemplate(fieldName, withHelper, emptySelection);
    select.setRenderer(
        new ComponentRenderer<>(te -> getOneLineComponentRenderer(te.mailingName, te.description)));
    return select;
  }

  public static Select<Drug> getDrugSelect(
      String fieldName, boolean withHelper, String emptySelection) {
    Select<Drug> select = getSelectTemplate(fieldName, withHelper, emptySelection);
    select.setRenderer(
        new ComponentRenderer<>(
            te -> getOneLineComponentRenderer(te.name, te.strength.toString())));
    return select;
  }

  public static Select<User> getUserSelect(
      String fieldName, boolean withHelper, String emptySelection) {
    Select<User> select = getSelectTemplate(fieldName, withHelper, emptySelection);
    select.setRenderer(
        new ComponentRenderer<>(te -> getOneLineComponentRenderer(te.userName, te.getFullName())));
    return select;
  }

  public static String getCountryFlag(String code) {
    if (code == null) return "";
    Emoji flag = EmojiManager.getForAlias(code.toLowerCase());
    String flag2 = flag == null ? code : flag.getUnicode();
    return flag2;
  }

  public static <T> ComboBox<T> getComboBoxTemplate(
      String fieldName, boolean withHelper, boolean readOnly) {
    ComboBox<T> comboBox = new ComboBox<>();
    comboBox.setAllowCustomValue(true);

    comboBox.setLabel(comboBox.getTranslation(fieldName));
    if (withHelper) {
      String helper = comboBox.getTranslation(fieldName + ".tt");
      comboBox.setHelperText(helper);
      comboBox.addThemeVariants(ComboBoxVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    comboBox.setReadOnly(readOnly);

    return comboBox;
  }

  public static Select<String> getSelect(List<String> items, String fieldName) {
    return getSelect(items, fieldName, false);
  }

  public static Select<String> getSelect(List<String> items, String fieldName, boolean withHelper) {
    Select<String> select = new Select<>();
    select.setItems(items);
    setSelectProperties(select, fieldName, withHelper, false);
    return select;
  }

  public static Select<String> getSelect(String group, String fieldName) {
    return getSelect(group, fieldName, false, false);
  }

  public static void setSelectProperties(
      Select<?> select, String fieldName, boolean withHelper, boolean readOnly) {
    select.setLabel(select.getTranslation(fieldName));
    if (withHelper) {
      String helper = select.getTranslation(fieldName + ".tt");
      select.setHelperText(helper);
      select.addThemeVariants(SelectVariant.LUMO_HELPER_ABOVE_FIELD);
    }
    select.setReadOnly(readOnly);
  }

  public static Select<String> getSelect(
      String group, String fieldName, boolean withHelper, boolean readOnly) {
    Select<String> select = UIUtils.getSelect(group);
    setSelectProperties(select, fieldName, withHelper, readOnly);
    return select;
  }

  /**
   * create and return a {@link Select} with supportet languages. on selection this component
   * trigger a change in the session language and set the current Locale to the selected language
   *
   * @return language selector
   */
  public static Select<String> getLanguageSelect() {
    Select<String> languages = UIUtils.getSelect("languages");
    String currLang = VaadinSession.getCurrent().getLocale().getLanguage();
    languages.setValue(currLang);
    languages.addValueChangeListener(
        evt -> {
          String lan = languages.getValue();
          VaadinSession.getCurrent().setLocale(new Locale(lan));
          UI.getCurrent().getPage().reload();
        });
    return languages;
  }

  public static Select<String> getSelect(String group) {
    List<String> items = TranslationProvider.getKeys(group);
    Select<String> select = new Select<>();

    int idx = items.indexOf("000");
    if (idx >= 0) {
      String p1 = items.remove(idx);
      select.setPlaceholder(p1);
    }
    select.setItems(items);
    select.setValue(items.get(0));
    select.setItemLabelGenerator(key -> TranslationProvider.getString(group, key));
    return select;
  }

  public static boolean showNotification(Response response) {
    return showNotification(response, false, false);
  }

  /**
   * show a standar backend Response as Vaadin Notification. this method also return true if the
   * response was a positive response. (not an error)
   *
   * @param response - the response
   * @param onlyOnError - true if you want only show error responses. not ok responses
   * @param whitCloseButton - append a close button
   * @return true for positive, false in case the response was an error responce
   */
  public static boolean showNotification(
      Response response, boolean onlyOnError, boolean whitCloseButton) {
    boolean isOk = response.getStatus() < 400;
    if (isOk && onlyOnError) return true;
    String title =
        isOk
            ? TranslationProvider.getTranslation("Response.ok.title")
            : TranslationProvider.getTranslation("Response.error.title");
    VaadinIcon icon = isOk ? VaadinIcon.INFO_CIRCLE_O : VaadinIcon.WARNING;
    String color = isOk ? LumoUtility.TextColor.SUCCESS : LumoUtility.TextColor.ERROR;
    String message = ((Map<?, ?>) response.getEntity()).get("message").toString();
    int duration = 0;
    if (!whitCloseButton)
      duration = isOk ? NOTIFICATION_INFO_DURATION : NOTIFICATION_ERROR_DURATION;
    showNotification(icon, color, title, message, duration);
    return isOk;
  }

  public static void showErrorNotification(String messageId, Object... parms) {
    String title = (new Span()).getTranslation(messageId + ".title", parms);
    String message = (new Span()).getTranslation(messageId + ".message", parms);
    showNotification(
        VaadinIcon.WARNING,
        LumoUtility.TextColor.ERROR,
        title,
        message,
        NOTIFICATION_ERROR_DURATION);
  }

  public static void showErrorNotification(String title, String message) {
    showNotification(
        VaadinIcon.WARNING,
        LumoUtility.TextColor.ERROR,
        title,
        message,
        NOTIFICATION_ERROR_DURATION);
  }

  public static void showSuccesNotification(String messageId) {
    String title = (new Span()).getTranslation(messageId + ".title");
    String message = (new Span()).getTranslation(messageId + ".message");
    showNotification(
        VaadinIcon.INFO_CIRCLE_O,
        LumoUtility.TextColor.SUCCESS,
        title,
        message,
        NOTIFICATION_INFO_DURATION);
  }

  public static void showNotification(
      VaadinIcon icon, String titleColor, String title, String message, int duration) {
    Notification notification = new Notification();
    notification.setDuration(duration);
    notification.setPosition(Position.BOTTOM_END);

    Button closeButton = new Button(VaadinIcon.CLOSE_SMALL.create());
    closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
    closeButton.addClickListener(event -> notification.close());

    Icon icon2 = icon.create();
    icon2.addClassNames(titleColor, LumoUtility.IconSize.LARGE);

    Div title2 = new Div(new Text(title));
    title2.addClassNames(titleColor, LumoUtility.FontSize.LARGE);

    if (!message.startsWith("<")) message = "<p>" + message + "</p>";
    Div info = new Div(title2, new Html(message));

    HorizontalLayout layout;
    if (duration <= 0) layout = new HorizontalLayout(icon2, info, closeButton);
    else layout = new HorizontalLayout(icon2, info);

    notification.add(layout);
    notification.open();
  }

  public static String getFormatedLocalDateTime(LocalDateTime localDateTime) {
    if (localDateTime == null) return "";
    return dateTimeFormatter.format(localDateTime);
  }

  public static void setIdColumn(Grid<? extends TAEntity> grid) {
    grid.addColumn("id")
        .setHeader(grid.getTranslation("TAEntity.id"))
        .setComparator(te -> te.id)
        .setTextAlign(ColumnTextAlign.END)
        .setWidth("250px")
        .setFlexGrow(0);
  }

  public static ListItem getListItem(
      String primaryKey, String secondaryKey, String image, Object... parms) {
    String primary = TranslationProvider.getTranslation(primaryKey, parms);
    String secondary = TranslationProvider.getTranslation(secondaryKey, parms);
    ListItem item = new ListItem(primary, secondary);
    if (image != null) {
      Avatar avatar = new Avatar();
      avatar.setImage(image);
      item.setPrefix(avatar);
    }
    return item;
  }

  public static ListItem getPrimarySecondaryListItem(
      String primaryKey, String secondaryKey, Object... parms) {
    return getListItem(primaryKey, secondaryKey, null, parms);
  }

  public static ListItem getPrimarySecondaryRender(String primary, String secondary) {
    ListItem item = new ListItem(primary, secondary);
    return item;
  }

  /**
   * Gets the number of seconds in this duration.
   *
   * @param duration - the string representing a duration
   * @return the seconds
   */
  public static int getSeconds(String duration) {
    Duration d = Duration.parse(duration);
    return (int) d.getSeconds();
  }

  /**
   * return the icon name retrived from the value. the value argument generaly is a internal value.
   * this value will be transformed to lowercase and the icon must be a png in icons folder
   *
   * @param value - original valus
   * @return the name
   */
  public static String getIconFrom(String value) {
    return ICON_PATH + value.toLowerCase() + ".png";
  }

  public static Div getXSmallSeparator() {
    Div div = new Div();
    div.addClassNames(LumoUtility.Height.XSMALL);
    return div;
  }

  public static Image getImageIcon(String source) {
    return getImageIcon(source, "30px");
  }

  public static Image getImageIcon(String source, String size) {
    return getImageIcon(source, size, size);
  }

  /**
   * return a standar image with all attributtet setted
   *
   * @param source - image source. if null, the returned image has no source
   * @param size - Height und Width for the image
   * @return a image
   */
  public static Image getImageIcon(String source, String sizeWith, String sizeHeight) {
    Image image = new Image();
    if (sizeWith != null) image.setWidth(sizeWith);
    if (sizeHeight != null) image.setHeight(sizeHeight);
    if (source != null) setImage(image, source);
    return image;
  }

  public static Hr getHrSeparator() {
    Hr hr = new Hr();
    hr.setHeight("2px");
    hr.addClassNames(LumoUtility.Margin.Top.LARGE, LumoUtility.BorderColor.CONTRAST_30);
    return hr;
  }

  public static HorizontalLayout getOrSeparator(String bundleId) {
    VerticalLayout vl = new VerticalLayout(new Hr());
    vl.setWidth("50%");
    vl.setPadding(false);
    vl.setMargin(false);

    VerticalLayout vr = new VerticalLayout(new Hr());
    vr.setWidth("50%");
    vr.setPadding(false);
    vr.setMargin(false);

    Span span = new Span();
    if (bundleId != null) {
      span.setText(span.getTranslation(bundleId));
    }

    HorizontalLayout layout = new HorizontalLayout(vl, span, vr);
    layout.setAlignItems(Alignment.CENTER);
    return layout;
  }

  public static void setSize(HasSize hasSize, int size) {
    hasSize.setWidth(size + "px");
    hasSize.setHeight(size + "px");
  }

  public static StreamResource getStreamResource(String source) {
    File file = new File(source);
    StreamResource resource =
        new StreamResource(
            "image",
            () -> {
              try {
                FileInputStream inputStream = new FileInputStream(file);
                return inputStream;
              } catch (Exception e) {
                Log.error(e);
                return null;
              }
            });
    return resource;
  }

  public static void setImage(Component image, String source) {
    if (image == null || source == null) return;

    if (!(image instanceof Image || image instanceof Avatar))
      throw new IllegalArgumentException(INVALID_IMAGE_COMPONENT);

    // static content or external url source
    if (image instanceof Image) ((Image) image).setSrc(source);
    else ((Avatar) image).setImage(source);
  }

  public static Component getOnlyTrueBooleanIcon(boolean b) {
    if (!b) return new Span();
    return getBooleanIcon(b);
  }

  public static Icon getBooleanIcon(boolean b) {
    Icon icon = new Icon(b ? VaadinIcon.CHECK : VaadinIcon.MINUS);
    icon.addClassNames(
        LumoUtility.IconSize.SMALL,
        (b ? LumoUtility.TextColor.SUCCESS : LumoUtility.TextColor.DISABLED));
    return icon;
  }

  /**
   * create a standar footer component
   *
   * @return footer component
   */
  public static HorizontalLayout getFooter() {
    HorizontalLayout footer = getCompactHorizontalLayout();
    footer.setPadding(true);
    footer.setSpacing(true);
    footer.setJustifyContentMode(JustifyContentMode.END);
    footer.addClassNames(
        LumoUtility.Border.TOP,
        LumoUtility.BorderColor.CONTRAST_10,
        LumoUtility.Background.CONTRAST_5);
    return footer;
  }

  public static HorizontalLayout getCompactHorizontalLayout(Component... childrens) {
    return getCompactHorizontalLayout(false, childrens);
  }

  public static HorizontalLayout getCompactHorizontalLayout(
      boolean centered, Component... childrens) {
    HorizontalLayout layout = new HorizontalLayout(childrens);
    setCompatStyle(layout);
    if (centered) {
      layout.setAlignItems(FlexComponent.Alignment.CENTER);
      layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }
    return layout;
  }

  public static void setCompatStyle(HorizontalLayout layout) {
    layout.setPadding(false);
    layout.setSpacing(false);
    layout.setMargin(false);
    layout.setWidthFull();
  }

  /**
   * create and return a standar footer component with DELETE_FORM, MOCK_FORM, CANCEL_FORM,
   * UPDATE_FORM, SAVE_FORM butons
   *
   * @param listener - the listener for all button.s click
   * @return footer with buttons
   */
  public static HorizontalLayout getCRUDFooter(
      ComponentEventListener<ClickEvent<Button>> listener) {
    return getCRUDFooter(listener, DELETE_FORM, CANCEL_FORM, SAVE_FORM);
  }

  public static HorizontalLayout getCRUDFooter(
      ComponentEventListener<ClickEvent<Button>> listener, String... actions) {
    HorizontalLayout footer = getFooter();
    footer.add(getCRUDButtons(listener, actions).toArray(new Button[0]));
    return footer;
  }

  public static List<Component> getCRUDButtons(
      ComponentEventListener<ClickEvent<Button>> listener) {
    return getCRUDButtons(listener, DELETE_FORM, CANCEL_FORM, SAVE_FORM);
  }

  public static List<Component> getCRUDButtons(
      ComponentEventListener<ClickEvent<Button>> listener, String... actions) {
    List<String> actionsToReturn = Arrays.asList(actions);
    ArrayList<Component> footer = new ArrayList<>();

    if (actionsToReturn.contains(UIUtils.DELETE_FORM)) {
      Button delete = new Button();
      delete.setText(delete.getTranslation(UIUtils.DELETE_FORM));
      delete.setId(UIUtils.DELETE_FORM);
      delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
      delete.getStyle().set("margin-inline-end", "auto");
      if (listener != null) delete.addClickListener(listener);
      footer.add(delete);
    }

    if (actionsToReturn.contains(UIUtils.MOCK_FORM)) {
      Button mock = new Button();
      mock.setText(mock.getTranslation(UIUtils.MOCK_FORM));
      mock.setId(UIUtils.MOCK_FORM);
      if (listener != null) mock.addClickListener(listener);
      footer.add(mock);
    }

    if (actionsToReturn.contains(UIUtils.CANCEL_FORM)) {
      Button cancel = new Button();
      cancel.setText(cancel.getTranslation(UIUtils.CANCEL_FORM));
      cancel.setId(UIUtils.CANCEL_FORM);
      if (listener != null) cancel.addClickListener(listener);
      footer.add(cancel);
    }

    if (actionsToReturn.contains(UIUtils.UPDATE_FORM)) {
      Button update = new Button();
      update.setText(update.getTranslation(UIUtils.UPDATE_FORM));
      update.setId(UIUtils.UPDATE_FORM);
      if (listener != null) update.addClickListener(listener);
      footer.add(update);
    }

    if (actionsToReturn.contains(UIUtils.SAVE_FORM)) {
      Button save = new Button();
      save.setText(save.getTranslation(UIUtils.SAVE_FORM));
      save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
      save.setId(UIUtils.SAVE_FORM);
      if (listener != null) save.addClickListener(listener);
      footer.add(save);
    }

    if (actionsToReturn.contains(UIUtils.OK_FORM)) {
      Button save = new Button();
      save.setText(save.getTranslation(UIUtils.OK_FORM));
      save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
      save.setId(UIUtils.OK_FORM);
      if (listener != null) save.addClickListener(listener);
      footer.add(save);
    }

    return footer;
  }

  public static Image getAppLogo(String theme) {
    String logoName = "goodfunds-logo-" + theme;
    Image image = new Image(ICON_PATH + logoName + ".svg", logoName);
    image.setWidth("200px");
    image.setHeight("64px");
    return image;
  }

  public static void animateError(HasStyle hasStyle) {
    AnimationBuilder.createBuilderFor(hasStyle)
        .create(AnimationTypes.AttentionSeekerAnimation.class)
        .withEffect(AttentionSeeker.shakeX)
        .withSpeed(Speed.fast) // optional
        .withDelay(Delay.noDelay) // optional
        .withRepeat(Repeat.Once) // optional
        .start();
  }

  private UIUtils() {}

  public static AceEditor getAceEditor(AceMode aceMode, List<String> variables) {
    AceEditor aceEditor = new AceEditor();
    aceEditor.setAutoComplete(true);
    aceEditor.setHeight("150px");
    aceEditor.setMode(aceMode);
    if (variables != null)
      aceEditor.setCustomAutoCompletion(variables.toArray(new String[0]), "Variable");
    return aceEditor;
  }

  public static AceEditor getAceEditor(AceMode aceMode) {
    AceEditor aceEditor = new AceEditor();
    aceEditor.setMode(aceMode);
    aceEditor.setReadOnly(true);
    aceEditor.setShowGutter(false);
    return aceEditor;
  }

  // public static GOnboarding getOnboarding(String task, Component component) {
  //   String taskTip = task + ".step";

  //   // select all .titles and use as index to build the tasklist
  //   List<String> steps = new ArrayList<>();
  //   ResourceBundle bundle =
  //       ResourceBundle.getBundle(
  //           TranslationProvider.RESOURCE_BUNDLE_NAME, VaadinSession.getCurrent().getLocale());
  //   for (String bundleKey : bundle.keySet()) {
  //     if (bundleKey.startsWith(taskTip) && bundleKey.endsWith("title")) {
  //       // remove "title" sufix the remaind strin look like: task.ClassName-##-
  //       steps.add(bundleKey.replace("title", ""));
  //     }
  //   }
  //   Collections.sort(steps);

  //   GOnboarding onboarding = new GOnboarding();
  //   MainLayout mainLayout = MainLayout.getInstance(component);
  //   for (String stepId : steps) {
  //     String pointTo = TranslationProvider.getTranslation(stepId + "point");
  //     Component target = UIUtils.findComponentById(mainLayout, pointTo);
  //     GOnboardingStep step2 = new GOnboardingStep(target, stepId);
  //     onboarding.addStep(step2);
  //   }

  //   return onboarding;
  // }

  public static Select<String> getCountryCodeSelect(String fieldName) {
    return getCountryCodeSelect(fieldName, false, null, null);
  }

  public static Select<String> getCountryCodeSelect(
      String fieldName, boolean withHelper, String region, String emptySelection) {
    List<Country> countries = Country.listAll();
    List<String> items = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    countries.forEach(
        c -> {
          if (region == null || (region != null && c.region.contains(region))) {
            items.add(c.code);
            map.put(c.code, c.getName());
          }
        });
    Select<String> select = getSelect(items, fieldName, withHelper);
    select.setRenderer(
        new ComponentRenderer<>(
            code -> {
              Div div = getOneLineComponentRenderer(getCountryFlag(code), map.get(code));
              return div;
            }));

    select.setValue(items.get(0));
    select.setEmptySelectionAllowed(false);

    if (emptySelection != null) {
      select.setEmptySelectionAllowed(true);
      select.setEmptySelectionCaption(select.getTranslation(emptySelection));
    }

    return select;
  }

  public static Component findComponentById(Component parent, String id) {
    List<Component> components = new ArrayList<>();
    components.addAll(parent.getChildren().collect(Collectors.toList()));
    for (Component child : components) {
      Optional<String> optional = child.getId();
      if (optional.isPresent() && optional.get().equals(id)) {
        return child; // found it!
      } else {
        Component result = findComponentById(child, id);
        if (result != null) {
          return result;
        }
      }
    }
    return null; // none was found
  }

  /**
   * return the area designated as input(login) or ouput 404 page info
   *
   * @return the area
   */
  public static VerticalLayout getInputOuputArea() {
    VerticalLayout layout = new VerticalLayout();
    layout.setPadding(false);
    layout.setSpacing(false);
    layout.getStyle().set("background", "hsla(0, 100%, 100%, 0.93)");
    layout.setMinWidth(UIUtils.LOGGING_FORM_WITH);
    layout.setMinHeight(UIUtils.LOGGING_FORM_HEIGHT);
    layout.setWidth(UIUtils.LOGGING_FORM_WITH);
    setGroupStyle(layout);
    return layout;
  }

  /**
   * return a Thumbnail of the youtube url main video
   *
   * @param url - the url
   * @return the Thumbnail.s url
   */
  public static String getVideoThumbnail(String url) {
    String pattern = "watch/?.*v=([^&]*)";
    Pattern compiledPattern = Pattern.compile(pattern);
    Matcher matcher = compiledPattern.matcher(url);
    if (matcher.find()) {
      String id = matcher.group(1);
      String url3 = "https://img.youtube.com/vi/" + id + "/0.jpg";
      return url3;
    }
    return null;
  }

  public static String getSocialMediaIcon(String url) {
    String icon = ICON_PATH + "no_social_network_icon.png";
    if (url.contains("facebook.com")) icon = ICON_PATH + "facebook.png";
    if (url.contains("instagram.com")) icon = ICON_PATH + "instagram.png";
    if (url.contains("tiktok.com")) icon = ICON_PATH + "tiktok.png";
    if (url.contains("linkedin.com")) icon = ICON_PATH + "linkedin.png";
    if (url.contains("youtube.com")) icon = ICON_PATH + "youtube.png";
    if (url.contains("twitter.com")) icon = ICON_PATH + "twitter.png";
    return icon;
  }

  /**
   * retrun true if the fileName is a member of the files types fileExt
   *
   * @param fileName - the file name (with extention)
   * @param fileExt - the list of extentions
   * @return true if the file is a member
   */
  public static boolean isFileMemberOf(String fileName, String... fileExt) {
    boolean ok = false;
    for (String ext : fileExt) {
      if (fileName.endsWith(ext.trim())) ok = true;
    }
    return ok;
  }

  public static VerticalLayout getWrapForCheckBox(Checkbox checkbox) {
    Span label = UIUtils.getSecondarySmallLabel(checkbox.getId().orElse("") + ".tt");
    VerticalLayout layout = new VerticalLayout(checkbox, label);
    layout.setSpacing(false);
    layout.setPadding(false);
    layout.addClassNames(LumoUtility.Margin.Top.MEDIUM, LumoUtility.LineHeight.NONE);
    return layout;
  }

  public static Component getHistoryRender(History history) {
    String mood = TranslationProvider.getString("history.mood", history.mood).trim();
    Span avatar2 = new Span(mood.substring(0, 2));
    avatar2.addClassName(LumoUtility.FontSize.XLARGE);
    Span name = new Span(mood.substring(2));

    Div owner = new Div(avatar2, name);
    owner.addClassNames(AlignItems.CENTER, Display.FLEX, Gap.MEDIUM);
    return owner;
  }

  public static Component getDrugRender(Drug drug) {
    String secondL = drug.secundaryColor == null ? "Drug.color.one" : "Drug.color.two";
    secondL = TranslationProvider.getTranslation(secondL, drug.primaryColor, drug.secundaryColor);
    return getImageRender(drug.imageMedicament, drug.name, secondL);
  }

  public static Component getMessagingProviderRender(MessagingProvider provider) {
    return getImageRender(provider.getIcon(), provider.getName(), null);
  }

  public static Component getTamagotchiRender(Tamagotchi tamagotchi) {
    return getAvatarRender(tamagotchi.avatar, tamagotchi.name, null);
  }

  public static Component getAdutRender(Adult adult) {
    return getAvatarRender(adult.avatar, adult.firstName + " " + adult.lastName, null);
  }

  public static Component getUserRender(User user) {
    return getAvatarRender(user.avatar, user.firstName + " " + user.lastName, user.email);
  }

  public static Component getSmallImageRender(String image, String firstLine, String secondLine) {
    return getImageRender(image, firstLine, secondLine, "20px");
  }

  public static Component getImageRender(String image, String firstLine, String secondLine) {
    return getImageRender(image, firstLine, secondLine, "30px");
  }

  public static Component getImageRender(
      String image, String firstLine, String secondLine, String size) {
    Image image2 = getImageIcon(image, size);

    Span firstLine2 = new Span(firstLine);

    Span secondLine2 = new Span(secondLine);
    secondLine2.addClassNames(FontSize.SMALL, TextColor.SECONDARY);

    Div firstAndSecod = new Div(firstLine2, secondLine2);
    firstAndSecod.addClassNames(Display.FLEX, FlexDirection.COLUMN);

    Div owner = new Div(image2, firstAndSecod);
    owner.addClassNames(AlignItems.CENTER, Display.FLEX, Gap.MEDIUM);
    return owner;
  }

  public static Component getAvatarRender(String avatar, String firstLine, String secondLine) {
    Avatar avatar2 = new Avatar(firstLine);

    UIUtils.setImage(avatar2, avatar);

    Span firstLine2 = new Span(firstLine);

    Span secondLine2 = new Span(secondLine);
    secondLine2.addClassNames(FontSize.SMALL, TextColor.SECONDARY);

    Div firstAndSecod = new Div(firstLine2, secondLine2);
    firstAndSecod.addClassNames(Display.FLEX, FlexDirection.COLUMN);

    Div owner = new Div(avatar2, firstAndSecod);
    owner.addClassNames(AlignItems.CENTER, Display.FLEX, Gap.MEDIUM);
    return owner;
  }

  
  public static RecordActions getRecordActions(
      Long id, TAView<?> listener, AditionalAction... aditionalActions) {
    MenuBar menuBar = new MenuBar();
    List<Component> components = new ArrayList<>();
    menuBar.addThemeVariants(MenuBarVariant.LUMO_ICON, MenuBarVariant.LUMO_TERTIARY);
    MenuItem burger =
        menuBar.addItem(VaadinIcon.ELLIPSIS_DOTS_V.create(), listener::menuIteClicked);
        components.add(burger);

    SubMenu burgerSubMenu = burger.getSubMenu();
    Icon icon = MaterialIcons.EDIT.create();
    // icon.getStyle().set("width", "var(--lumo-icon-size-s)");
    // icon.getStyle().set("height", "var(--lumo-icon-size-s)");
    // icon.getStyle().set("marginRight", "var(--lumo-space-s)");
    MenuItem edit = burgerSubMenu.addItem(icon, listener::menuIteClicked);
    edit.setId(EDIT_ACTION);
    edit.add(TranslationProvider.getTranslation(EDIT_ACTION));
    edit.getElement().setProperty(ENTITY_ID, "" + id);
    components.add(edit);

    for (AditionalAction action : aditionalActions) {
      MenuItem item =
          burgerSubMenu.addItem(
              new Icon(action.collectionName, action.iconName), listener::menuIteClicked);
      item.setId(action.actionName);
      item.getElement().setProperty(ENTITY_ID, "" + id);
      item.add(TranslationProvider.getTranslation(action.actionName));
      item.setEnabled(action.isEnabled);
    components.add(item);

    }

    // burgerSubMenu.addItem(new Hr());

    MenuItem delete =
        burgerSubMenu.addItem(LineAwesomeIcon.TRASH_ALT.create(), listener::menuIteClicked);
    delete.getElement().getStyle().set("color", "red");
    delete.setId(DELETE_ACTION);
    delete.add(TranslationProvider.getTranslation(DELETE_ACTION));
    delete.getElement().setProperty(ENTITY_ID, "" + id);
    delete.addThemeNames(LumoUtility.TextColor.ERROR);
    components.add(delete);

    RecordActions menuComponents = new RecordActions(menuBar, components);
    return menuComponents;
  }

  public static record RecordActions(MenuBar menuBar, List<Component> components) {}
  public static record AditionalAction(String actionName, String collectionName, String iconName, boolean isEnabled) {}

}
