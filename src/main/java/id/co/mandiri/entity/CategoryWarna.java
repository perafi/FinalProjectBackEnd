package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/*    id varchar(64) not null primary key,
            warna varchar(150) not null,
            kode varchar(64) not null,
            description text not null*/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warna_category")
public class CategoryWarna {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "id", nullable = false, length = 64)
    private String id;
    @Column(name = "warna", nullable = false, length = 150)
    private String warna;
    @Column(name = "kode", nullable = false, length = 64)
    private String kode;
    @Lob
    @Type(type = "text")
    @Column(name = "description")
    private String description;
}
