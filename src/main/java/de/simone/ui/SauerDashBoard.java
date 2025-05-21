package de.simone.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.IconSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import de.simone.*;
import de.simone.components.*;
import de.simone.components.list.*;
import de.simone.utilities.*;
import de.simone.views.components.*;
import jakarta.annotation.security.*;

import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("SauerDashBoard")
@RolesAllowed({ "Sitrone.master", "SauerDashBoard" })
@Route(value = "SauerDashBoard", layout = MainLayout.class)
public class SauerDashBoard extends ComponentView {

  public static final String PERSON_1 = "Ava Smith";
  public static final String PERSON_1_EMAIL = "ava.smith@company.com";
  public static final String PERSON_1_IMG =
      "https://images.unsplash.com/photo-1530785602389-07594beb8b73?w=160";

  public static final String PERSON_2 = "Emma Johnson";
  public static final String PERSON_2_EMAIL = "emma.johnson@company.com";
  public static final String PERSON_2_IMG =
      "https://images.unsplash.com/photo-1553514029-1318c9127859?w=160";

  public static final String PERSON_3 = "Mia Williams";
  public static final String PERSON_3_EMAIL = "mia.williams@company.com";
  public static final String PERSON_3_IMG =
      "https://images.unsplash.com/photo-1580489944761-15a19d654956?w=160";

  public static final String LOREM_IPSUM =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut"
          + " labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"
          + " ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"
          + " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
          + " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt"
          + " mollit anim id est laborum.";

  public SauerDashBoard() {

    Preview publicInfo = new Preview(createPublicInformation());
    Preview contactInfo = new Preview(createContactInformation());
    Preview timeLine = new Preview(createTimeline());
    Preview actions = new Preview(getActions());

    Layout layout = getLayout(publicInfo, contactInfo, timeLine, actions);
    layout.setColumns(Layout.GridColumns.COLUMNS_2);
    layout.setRowGap(Layout.Gap.MEDIUM);
    layout.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_FULL, timeLine, actions);

    add(layout);
  }

  private Layout createTimeline() {
    Component title =
        getH2(
            "Public information",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"
                + " incididunt ut labore et dolore magna aliqua.");

    TimelineListItem item1 =
        new TimelineListItem(
            LineAwesomeIcon.CHECK_SOLID,
            Color.Background.SUCCESS,
            Color.Text.SUCCESS_CONTRAST,
            PERSON_1,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"
                + " incididunt ut labore et dolore magna aliquaincididunt ut labore et dolore magna"
                + " aliqua incididunt ut labore et dolore magna aliqua.",
            "1d ago");
    item1.setAvatarImage(PERSON_1_IMG);

    TimelineListItem item2 =
        new TimelineListItem(
            LineAwesomeIcon.TAG_SOLID,
            Color.Background.PRIMARY,
            Color.Text.PRIMARY_CONTRAST,
            PERSON_2,
            new Span(
                new Text(" added labels "),
                new Badge("bug", BadgeVariant.ERROR, BadgeVariant.PILL),
                new Text(" "),
                new Badge("a11y", BadgeVariant.SUCCESS, BadgeVariant.PILL)),
            "4d ago");
    item2.setAvatarImage(PERSON_2_IMG);

    TimelineListItem item3 =
        new TimelineListItem(
            LineAwesomeIcon.PEN_ALT_SOLID,
            Color.Background.CONTRAST,
            Color.Text.PRIMARY_CONTRAST,
            PERSON_3,
            "changed the title",
            "5d ago");
    item3.setAvatarImage(PERSON_3_IMG);

    TimelineListItem item4 =
        new TimelineListItem(
            LineAwesomeIcon.LIGHTBULB, "This is an API test by <b>" + PERSON_1 + "</b>", "3d ago");

    List list = new List(item1, item2, item3, item4);

    Layout layout = getLayout(title, list);
    layout.setColumns(Layout.GridColumns.COLUMNS_1);

    return layout;
  }

  private List createRelativesList() {
    List list = new List();
    list.setAutoFill(200, Unit.PIXELS);
    list.setGap(Layout.Gap.MEDIUM);

    ImageListItem item =
        new ImageListItem(
            "https://images.unsplash.com/photo-1511884642898-4c92249e20b6?w=640",
            "Aerial shot of forest",
            "Pine Watt",
            "November 28, 2017",
            createIconButton(LineAwesomeIcon.PLUG_SOLID, "New"),
            createIconButton(LineAwesomeIcon.NEOS, "New"),
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions"));
    item.addClickListener(e -> System.out.println(".()"));

    list.add(
        item,
        new ImageListItem(
            "https://images.unsplash.com/photo-1434725039720-aaad6dd32dfe?w=640",
            "Photo of green grass field at sunrise",
            "Ales Krivec",
            "June 19, 2015",
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions")),
        new ImageListItem(
            "https://images.unsplash.com/photo-1532274402911-5a369e4c4bb5?w=640",
            "Brown wooden dock between lavender flower field near body of water during golden hour",
            "Mark Harpur",
            "July 22, 2018",
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions")),
        new ImageListItem(
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470?w=640",
            "Three brown wooden boat on blue lake water taken at daytime",
            "Pietro De Grandi",
            "August 3, 2017",
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions")),
        new ImageListItem(
            "https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=640",
            "Brown house near body of water",
            "Luca Bravo",
            "August 9, 2016",
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions")),
        new ImageListItem(
            "https://images.unsplash.com/photo-1433838552652-f9a46b332c40?w=640",
            "Hot air balloon contest",
            "Daniela Cuevas",
            "June 9, 2015",
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions")),
        new ImageListItem(
            "https://images.unsplash.com/photo-1419242902214-272b3f66ee7a?w=640",
            "Silhouette photo of mountain during night time",
            "Vincentiu Solomon",
            "December 22, 2014",
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions")),
        new ImageListItem(
            "https://images.unsplash.com/photo-1468276311594-df7cb65d8df6?w=640",
            "Milky way above body of water",
            "Kristopher Roller",
            "July 12, 2016",
            createIconButton(LineAwesomeIcon.ELLIPSIS_V_SOLID, "Actions")));
    return list;
  }

  private Button createIconButton(LineAwesomeIcon icon, String ariaLabel) {
    Button button = new Button(icon.create());
    button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    button.setAriaLabel(ariaLabel);
    button.setTooltipText(ariaLabel);
    return button;
  }

  public Component createPublicInformation() {
    Component title =
        getH2(
            "Public information",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"
                + " incididunt ut labore et dolore magna aliqua.");

    Avatar avatar = new Avatar("Emily Johnson");
    avatar.setWidth("100px");
    avatar.setHeight("100px");
    // avatar.addThemeVariants(AvatarVariant.LUMO_XLARGE);
    avatar.setImage(
        "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80");

    Button uploadButton = new Button("Upload");
    uploadButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    Upload upload = new Upload();
    upload.setDropAllowed(false);
    upload.setMaxFiles(1);
    upload.setUploadButton(uploadButton);

    Button delete = new Button("Delete");
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);

    Layout avatarLayout = new Layout(avatar, upload, delete);
    avatarLayout.addClassNames(Margin.Bottom.XSMALL, Margin.Top.MEDIUM);
    avatarLayout.setAlignItems(Layout.AlignItems.CENTER);
    avatarLayout.setGap(Layout.Gap.LARGE);

    TextField username = new TextField("Username");
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");

    Layout layout = new Layout(title, avatarLayout, username, firstName, lastName);
    // Viewport < 1024px
    layout.setFlexDirection(Layout.FlexDirection.COLUMN);
    // Viewport > 1024px
    layout.setDisplay(Breakpoint.LARGE, Layout.Display.GRID);
    layout.setColumns(Layout.GridColumns.COLUMNS_2);
    layout.setColumnGap(Layout.Gap.MEDIUM);
    layout.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_FULL, title, avatarLayout, username);
    return layout;
  }

  /**
   * create a Layout with standar parameters
   *
   * @param components - the components
   * @return the Layout
   */
  public static Layout getLayout(Component... components) {
    Layout layout = new Layout(components);
    // Viewport < 1024px
    layout.setFlexDirection(Layout.FlexDirection.COLUMN);
    // Viewport > 1024px
    layout.setDisplay(Breakpoint.LARGE, Layout.Display.GRID);
    layout.setColumnGap(Layout.Gap.MEDIUM);
    layout.setColumns(Layout.GridColumns.COLUMNS_4);

    return layout;
  }

  public Component createContactInformation() {
    Component title =
        getH2(
            "Contact information",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor"
                + " incididunt ut labore et dolore magna aliqua.");

    TextField address = new TextField("Address");
    TextField city = new TextField("City");
    ComboBox state = new ComboBox("State");
    TextField zip = new TextField("ZIP");

    TextField phone = new TextField("Phone");
    phone.setPrefixComponent(LineAwesomeIcon.PHONE_SOLID.create());

    EmailField email = new EmailField("Email");
    email.setPrefixComponent(LineAwesomeIcon.ENVELOPE.create());

    Layout layout = new Layout(title, address, city, state, zip, phone, email);
    // Viewport < 1024px
    layout.setFlexDirection(Layout.FlexDirection.COLUMN);
    // Viewport > 1024px
    layout.setDisplay(Breakpoint.LARGE, Layout.Display.GRID);
    layout.setColumns(Layout.GridColumns.COLUMNS_4);
    layout.setColumnGap(Layout.Gap.MEDIUM);
    layout.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_2, city, phone, email);
    layout.setColumnSpan(Layout.ColumnSpan.COLUMN_SPAN_FULL, title, address);
    return layout;
  }

  public Layout getActions() {
    Highlights highlights = new Highlights();
    highlights.setColumnGap(Highlights.Gap.MEDIUM);
    highlights.setRowGap(Highlights.Gap.MEDIUM);

    Highlight2 highlight =
        new Highlight2(
            createIcon(LineAwesomeIcon.PEOPLE_CARRY_SOLID, null, Color.Text.PRIMARY),
            "zur gleichen Familie gehörend; gleicher Herkunft, Abstammung",
            "Verwandte");
    highlight.swichToAction();

    highlights.add(highlight);

    highlight =
        new Highlight2(
            createIcon(LineAwesomeIcon.PAGE4, Color.Background.SUCCESS_10, Color.Text.SUCCESS),
            "Schriftliche Anordnung des Arztes an den Apotheker zur Abgabe bestimmter Medikamente",
            "Rezepte");
    highlight.swichToAction();

    highlights.add(highlight);

    return highlights;
  }

  public static Component createIcon(
      LineAwesomeIcon icon, Color.Background background, Color.Text color) {
    SvgIcon i = icon.create();
    i.addClassNames(IconSize.LARGE);

    Layout container = new Layout(i);
    container.addClassNames(BorderRadius.LARGE, Height.XLARGE, Width.XLARGE);

    if (background != null) container.addClassName(background.getClassName());

    if (color != null) container.addClassName(color.getClassName());

    container.setAlignItems(Layout.AlignItems.CENTER);
    container.setJustifyContent(Layout.JustifyContent.CENTER);
    return container;
  }
}