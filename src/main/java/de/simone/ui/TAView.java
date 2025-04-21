package de.simone.ui;

import java.util.*;

import org.apache.commons.lang3.*;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.confirmdialog.*;
import com.vaadin.flow.component.contextmenu.*;
import com.vaadin.flow.component.dialog.*;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.grid.Grid.*;
import com.vaadin.flow.component.grid.dataview.*;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.page.*;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.dom.*;
import com.vaadin.flow.function.*;
import com.vaadin.flow.router.*;
import com.vaadin.flow.router.internal.*;
import com.vaadin.flow.server.*;
import com.vaadin.flow.server.auth.*;
import com.vaadin.flow.theme.lumo.*;

import de.simone.*;
import de.simone.backend.*;
import de.simone.components.*;
import de.simone.ui.components.*;
import io.quarkus.logging.*;
import jakarta.inject.*;
import jakarta.ws.rs.core.*;

public abstract class TAView<E extends TAEntity> extends TVerticalLayout
    implements BeforeEnterObserver,
        ComponentEventListener<ClickEvent<Button>>,
        PropertyChangeListener {

  protected static final String AUTH_ERROR_MSG = "Auth.error";


  public Grid<E> grid; // due tu UIUtils

  protected TAService<E> taService;
  protected Class<E> taEntity;
  protected String formUrl;
  protected String viewUrl;
  protected TextField searchField;
  protected AccessAnnotationChecker accessAnnotationChecker;
  protected ViewEmptyPage emptyPage;
  protected TTimer timer;
  protected Sidebar sidebar;
  protected VaadinContext context;

  private HastEntity<E> hastEntity;
  private List<Component> globalToolBar;
  private Class<? extends TAForm<E>> formClass;
  private HorizontalLayout paginationLayout;
  private IntegerField rowsPerPage;
  private TextField pageCounter;
  private Button firstPageButton;
  private Button prevPageButton;
  private Button nextPageButton;
  private Button lastPageButton;
  private ListDataRequest listRequest;
  private ListDataRequest orgListRequest;
  private ListDataProvider<E> listDataProvider;
  private String configDialogBundle;
  private String tipOftheDay;
  private String[] tasksToCheck;
  private Dialog configDialog;

  @Inject public UsersService usersService;
  @Inject QueriesService queriesService;
  @Inject MiscellaneousService miscellaneousService;

  private User currentUser;

  protected TAView() {
    accessAnnotationChecker = new AccessAnnotationChecker();
    timer = UIUtils.getTimer(this, 1);
    currentUser = SecurityUtils.getLoggedUser();
    pageCounter = new TextField();
    pageCounter.setReadOnly(true);
    pageCounter.setWidth("100px");
    pageCounter.addClassName(LumoUtility.Margin.Left.SMALL);
    rowsPerPage = new IntegerField();
    rowsPerPage.setWidth("50px");
    rowsPerPage.setValue(currentUser.rowsPerPage);
    rowsPerPage.addValueChangeListener(
        ev -> {
          currentUser.rowsPerPage = ev.getValue();
          usersService.save(currentUser, false);
          listRequest.limit = ev.getValue();
          setListRequest(listRequest);
        });
    firstPageButton = new Button(MaterialIcons.FIRST_PAGE.create(), this::paginate);
    firstPageButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    prevPageButton = new Button(MaterialIcons.NAVIGATE_BEFORE.create(), this::paginate);
    prevPageButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    nextPageButton = new Button(MaterialIcons.NAVIGATE_NEXT.create(), this::paginate);
    nextPageButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    lastPageButton = new Button(MaterialIcons.LAST_PAGE.create(), this::paginate);
    lastPageButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    paginationLayout =
        new HorizontalLayout(
            firstPageButton,
            prevPageButton,
            rowsPerPage,
            pageCounter,
            nextPageButton,
            lastPageButton);
    paginationLayout.setId(UIUtils.PAGINATION_LAYOUT);
    paginationLayout.setSpacing(false);
  }

  protected void addOneTimeTip(String tip) {
    boolean appended = UsersService.addOnTimeTip(tip);
    if (appended) {
      tipOftheDay = tip;
      timer.start();
    }
  }

  public Component getToolBarComponent(String id) {
    Optional<Component> optional =
        globalToolBar.stream().filter(c -> c.getId().orElse("null").equals(id)).findFirst();
    if (!optional.isPresent()) return null;
    return optional.get();
  }

  public void removeToolBarComponents(String... componentsId) {
    for (String id : componentsId) {
      Component component = getToolBarComponent(id);
      globalToolBar.remove(component);
    }
  }

  public void setQuery(Query query) {
    String patt = "order by";
    if (query != null) {
      queriesService.save(query);
      currentUser.addQuery(query);
      if (!StringUtils.isBlank(query.partialWhere)) {
        listRequest.partialWhere = query.partialWhere;
        String min = query.partialWhere.toLowerCase();
        // intercept order by
        if (min.contains(patt)) {
          String[] newSec = min.split(patt);
          String newWhere = query.partialWhere.substring(0, newSec[0].length()).trim();
          String newSort =
              query.partialWhere.substring(min.length() - newSec[1].length(), min.length()).trim();
          listRequest.partialWhere = newWhere;
          listRequest.sortBy = newSort;
        }
      }
    } else {
      listRequest = orgListRequest;
      currentUser.removeQuery(getClass());
    }
    usersService.save(currentUser, false);
    setListRequest(listRequest);
  }

  public List<Component> getGlobalToolBar() {
    return globalToolBar;
  }

  protected void init(
      Class<E> entityClass, Class<? extends TAForm<E>> formClass, TAService<E> service) {
    init(entityClass, formClass, service, true);
  }

  protected void init(
      Class<E> entityClass,
      Class<? extends TAForm<E>> formClass,
      TAService<E> service,
      boolean withToolBar) {

    this.context = VaadinService.getCurrent().getContext();
    this.taEntity = entityClass;
    this.taService = service;
    this.viewUrl = RouteUtil.resolve(context, getClass());
    this.formClass = formClass;
    this.formUrl = RouteUtil.resolve(context, formClass);

    if (withToolBar) {
      setToolBarComponents(UIUtils.getToolBar(this));
    }

    emptyPage = new ViewEmptyPage(this.getClass());
    emptyPage.setSizeFull();
    sidebar = new Sidebar();
    add(timer, grid, sidebar, emptyPage);
    setSizeFull();
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    if (tipOftheDay != null) {
      // GOnboarding onboarding = UIUtils.getOnboarding(tipOftheDay, this);
      // onboarding.start();
      timer.pause();
    }
  }

  protected void checkTasks(String... tasksToCheck) {
    this.tasksToCheck = tasksToCheck;
  }

  private void paginate(ClickEvent<Button> evt) {
    if (evt.getSource() == firstPageButton) listRequest.page = 0;
    if (evt.getSource() == prevPageButton) listRequest.page--;
    if (evt.getSource() == nextPageButton) listRequest.page++;
    if (evt.getSource() == lastPageButton) listRequest.page = listDataProvider.pages;

    setListRequest(listRequest);
  }

  protected void initListRequest(ListDataRequest request) {
    this.orgListRequest = request.copy();
    this.listRequest = request;
    User user = SecurityUtils.getLoggedUser();
    Query query = user.getQuery(getClass());
    if (query != null) {
      setQuery(query);
    } else {
      setListRequest(request);
    }
  }

  public void setItems(List<E> items) {
    setItems(items, null);
  }

  public void setItems(List<E> items, SerializablePredicate<E> filter) {
    GridListDataView<E> dataView = grid.setItems(items);
    searchField.addValueChangeListener(evt -> dataView.refreshAll());
    if (filter != null) {
      dataView.addFilter(filter);
    }

    boolean pendingTask = emptyPage.checkTasks(tasksToCheck);
    if (pendingTask) {
      for (Component component : globalToolBar) {
        if (component instanceof HasEnabled) ((HasEnabled) component).setEnabled(false);
      }
    }
    emptyPage.setVisible(pendingTask);
    grid.setVisible(!pendingTask);
  }

  public ListDataRequest getListRequest() {
    return listRequest;
  }

  protected void setListRequest(ListDataRequest request) {
    this.listRequest = request;
    listRequest.limit = currentUser.rowsPerPage;
    this.listDataProvider = taService.listImpl(request);
    firstPageButton.setEnabled(listDataProvider.page > 0);
    prevPageButton.setEnabled(listDataProvider.page > 0);
    nextPageButton.setEnabled(listDataProvider.page < listDataProvider.pages);
    lastPageButton.setEnabled(listDataProvider.page < listDataProvider.pages);
    setItems(listDataProvider.data);
    pageCounter.setValue(listDataProvider.page + " of " + listDataProvider.pages);
  }

  protected void setToolBarComponents(List<Component> globalToolBar) {
    this.globalToolBar = globalToolBar;
    this.searchField = (TextField) getToolBarComponent(UIUtils.SEARCH_FIELD);
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    getUI()
        .ifPresent(
            ui -> {
              Page page = ui.getPage();
              page.retrieveExtendedClientDetails(
                  details -> updateVisibleColumns(details.getBodyClientWidth()));
              page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            });
  }

  private void updateVisibleColumns(int width) {
    boolean mobile = width < UIUtils.MOBILE_BREAKPOINT;
    List<Grid.Column<E>> columns = grid.getColumns();

    // "Mobile" column
    columns.get(0).setVisible(mobile);

    // "Desktop" columns
    for (int i = 1; i < columns.size(); i++) {
      Column<E> column = columns.get(i);
      // sub classes can alter visibility of a column
      boolean isVisible = column.getElement().getProperty(UIUtils.COLUMN_VISIBLE, true);
      column.setVisible(!mobile && isVisible);
    }
  }

  /**
   * return the current selected element in the list or grid or null if nothing has be selected
   *
   * @return - the element
   */
  protected E getSelectedItem() {
    Optional<E> optional = grid.getSelectionModel().getFirstSelectedItem();
    if (optional.isPresent()) return optional.get();

    return null;
  }

  protected void setDialogElements(String bundle, HastEntity<E> body) {
    this.configDialogBundle = bundle;
    this.hastEntity = body;
  }

  protected void openConfigDialog(E entity) {
    hastEntity.setEntity(entity);
    Button acceptButton =
        UIUtils.getButton(configDialogBundle + ".accept", ButtonVariant.LUMO_PRIMARY);
    acceptButton.setId(UIUtils.SAVE_FORM);
    acceptButton.addClickListener(this);

    Button update = UIUtils.getButton(configDialogBundle + ".update", ButtonVariant.LUMO_TERTIARY);
    update.setId(UIUtils.UPDATE_FORM);
    update.addClickListener(this);

    configDialog =
        UIUtils.getDialog(
            configDialogBundle + ".title",
            null,
            Arrays.asList(update, acceptButton),
            configDialogBundle + ".cancel");

    configDialog.removeAll();
    configDialog.add((Component) hastEntity);
    configDialog.setWidth("90%");
    configDialog.setHeight("90%");
    configDialog.open();
  }

  protected void showForm(E entity) {
    try {
      TAForm<E> taForm = formClass.getDeclaredConstructor().newInstance();
      taForm.init(this);
      if (entity == null) entity = taService.get(0L);
      taForm.setEntity(entity);

      String entityName = getTranslation(taEntity.getSimpleName());

      String title =
          entity.isNewEntity()
              ? getTranslation("Sidebar.title.new", entityName)
              : getTranslation("Sidebar.title.edit", entityName);
      sidebar.createHeader(title, null);
      sidebar.setContent(taForm.getBodyComponents());
      sidebar.setFooter(taForm.getFooterComponents());
      sidebar.open();
    } catch (Exception e) {
      Log.error("", e);
    }
  }

  @Override
  public void onComponentEvent(ClickEvent<Button> event) {
    String cmpId = event.getSource().getId().orElse("null");

    if (cmpId.startsWith(UIUtils.NEW_ACTION)) {
      if (accessAnnotationChecker.hasAccess(formClass)) {
        showForm(null);
      } else {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
      }
      return;
    }

    if (cmpId.startsWith(UIUtils.EXPORT_CSV_ACTION)) {
      /**
       * this code fragment do nothig on user autorized and only present error message when not. see
       * #configureExport() method
       */
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".export");
      if (!ok) {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
        return;
      }
    }

    if (cmpId.startsWith(UIUtils.SAVE_FORM) && hastEntity.isValid()) {
      E entity = hastEntity.getEntity();
      Response response = taService.save(entity);
      if (UIUtils.showNotification(response)) {
        configDialog.close();
        UI.getCurrent().navigate(viewUrl);
      }
    }

    if (cmpId.startsWith(UIUtils.UPDATE_FORM) && hastEntity.isValid()) {
      E entity = hastEntity.getEntity();
      Response response = taService.save(entity);
      UIUtils.showNotification(response);
    }

    E selectedItem = getSelectedItem();
    if (selectedItem == null) return;

    if (cmpId.startsWith(UIUtils.EDIT_ACTION)) {
      if (accessAnnotationChecker.hasAccess(formClass)) {
        showForm(selectedItem);
      } else {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
      }
      return;
    }

    if (cmpId.startsWith(UIUtils.DUPLICATE_ACTION)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".copy");
      if (ok) {
        Response response = taService.duplicate(selectedItem.id);
        if (UIUtils.showNotification(response)) UI.getCurrent().navigate(viewUrl);
      } else {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
      }
      return;
    }

    if (cmpId.startsWith(UIUtils.DELETE_ACTION)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".delete");
      if (ok) {
        ConfirmDialog dialog = UIUtils.getDeleteDialog(taEntity);
        dialog.addConfirmListener(
            e -> {
              Response response = taService.delete(selectedItem.id);
              if (UIUtils.showNotification(response)) UI.getCurrent().navigate(viewUrl);
            });
        dialog.open();
      } else {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
      }
    }
  }

  protected E getSelectedItem(ClickEvent<MenuItem> event) {
    String entityId2 = event.getSource().getElement().getProperty(UIUtils.ENTITY_ID, "0");
    long id = Long.parseLong(entityId2);
    E selectedItem = null;
    com.vaadin.flow.data.provider.ListDataProvider<E> dataProvider =
        ((com.vaadin.flow.data.provider.ListDataProvider<E>) grid.getDataProvider());
    for (E entity2 : dataProvider.getItems()) {
      if (id == entity2.id) selectedItem = entity2;
    }
    return selectedItem;
  }

  public void menuIteClicked(ClickEvent<MenuItem> event) {
    String cmpId = event.getSource().getId().orElse("null");
    E selectedItem = getSelectedItem(event);
    if (selectedItem == null) return;

    if (cmpId.startsWith(UIUtils.EDIT_ACTION)) {
      if (accessAnnotationChecker.hasAccess(formClass)) {
        showForm(selectedItem);
      } else {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
      }
      return;
    }

    if (cmpId.startsWith(UIUtils.DELETE_ACTION)) {
      boolean ok = SecurityUtils.hasAccess(taEntity.getSimpleName() + ".delete");
      if (ok) {
        ConfirmDialog dialog = UIUtils.getDeleteDialog(taEntity);
        dialog.addConfirmListener(
            e -> {
              Response response = taService.delete(selectedItem.id);
              if (UIUtils.showNotification(response)) UI.getCurrent().navigate(viewUrl);
            });
        dialog.open();
      } else {
        UIUtils.showErrorNotification(AUTH_ERROR_MSG);
      }
    }
  }
}
