package de.simone.backend;

import de.simone.TranslationProvider;
import de.simone.UIUtils;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.andreinc.jbvext.annotations.misc.OneOfStrings;

@Entity
public class MessagingProvider extends TAEntity {

  public static final String WHATSAPP = "WHATSAPP";
  public static final String INSTAGRAM = "INSTAGRAM";

  @NotNull
  @OneOfStrings({WHATSAPP, INSTAGRAM})
  public String provider = WHATSAPP;

  @NotBlank
  @NotEmpty
  @Size(max = TAEntity.SIZE_255)
  public String accountName;

  @NotBlank
  @NotEmpty
  @Size(max = TAEntity.SIZE_255)
  public String clientId;

  @NotBlank
  @NotEmpty
  @Size(max = TAEntity.SIZE_255)
  public String secret;

  @NotBlank
  @NotEmpty
  @Size(max = TAEntity.SIZE_255)
  public String clientIdTest;

  @NotBlank
  @NotEmpty
  @Size(max = TAEntity.SIZE_255)
  public String secretTest;

  public String getName() {
    return TranslationProvider.getString("messagingProvider.provider", provider);
  }

  public String getIcon() {
    return UIUtils.ICON_PATH + provider.toLowerCase() + ".png";
  }
}
