package de.simone.backend;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ChatGroups")
public class ChatGroup extends TAEntity {

  @NotBlank
  @NotEmpty
  @Size(max = SIZE_255)
  public String name;

  @Size(max = SIZE_255)
  public String image;

  @Size(max = SIZE_255)
  public String lastMessage;

  @ElementCollection 
  public Set<Long> seenBy = new HashSet<>();

  @ElementCollection 
  public Set<Long> members = new HashSet<>();

  public int getUnreadMessages(Long re) {
    // ChatMessage.find(CSV_DATETIME_FORMAT, null);
    return 1;
  }

}
