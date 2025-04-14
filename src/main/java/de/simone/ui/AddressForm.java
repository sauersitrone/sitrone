package de.simone.ui;

import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import de.simone.UIUtils;
import de.simone.backend.Address;
import de.simone.ui.components.FileLoaderSimple;
import de.simone.ui.components.GroupPanel;

public class AddressForm extends GroupPanel {

    private TextField firstName;
    private TextField lastName;
    private TextField street;
    private TextField city;
    private TextField postcode;
    private Select<String> country;
    private Select<String> salutation;
    private Select<String> title;
    // private Select<String> type;
    private EmailField email;
    private TextField phone;
    private FileLoaderSimple foto;
    private FileLoaderSimple signature;

    protected BeanValidationBinder<Address> binder;

    public AddressForm() {
        this("address.separator", false, 3);
    }

    public AddressForm(boolean withImages) {
        this("address.separator", withImages, 3);
    }

    public AddressForm(String titleId, boolean withImages, int cols) {
        super(titleId);
        foto = new FileLoaderSimple("Address.foto");
        // foto.setPlaceHolder(UIUtils.YOUR_AVATAR_HERE);
        signature = new FileLoaderSimple("Address.signature");
        // signature.setPlaceHolder(UIUtils.YOUR_SIGNATURE_HERE);
        firstName = UIUtils.getTextField("Address.firstName");
        lastName = UIUtils.getTextField("Address.lastName");
        street = UIUtils.getTextField("Address.street");
        city = UIUtils.getTextField("Address.city");
        postcode = UIUtils.getTextField("Address.postcode");
        country = UIUtils.getCountryCodeSelect("Address.country");
        salutation = UIUtils.getSelect("salutations", "Address.salutation");
        title = UIUtils.getSelect("titles", "Address.title");
        // type = UIUtils.getSelect("contacts.type", "Address.type");
        email = UIUtils.getEmailField("Address.email");
        phone = UIUtils.getTextField("Address.phone");
        phone.setRequiredIndicatorVisible(false);

        Hr hr = UIUtils.getHrSeparator();
        // setBodyComponents(salutation, title, firstName, lastName, hr, street, city,
        // postcode, country, type, email, phone);
        setBodyComponents(salutation, title, firstName, lastName, hr, street, city, postcode,
                country, email, phone);
        getBodyLayout().setColspan(hr, cols);

        // images & signature
        if (withImages) {
            Hr hr2 = UIUtils.getHrSeparator();
            getBodyLayout().add(hr2, foto, signature);
            getBodyLayout().setColspan(hr2, cols);
        }

        this.binder = new BeanValidationBinder<>(Address.class);
        binder.bindInstanceFields(this);
    }
}
