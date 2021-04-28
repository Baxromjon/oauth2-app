package ai.ecma.oauth2app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String contentType;

    private long size;

    @OneToOne(mappedBy = "attachment", cascade = CascadeType.ALL)
    private AttachmentContent attachmentContent;

}
