package com.mb.mysteryboxpe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BoxVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "id_plantilla")
    private Plantilla plantilla;

    @ManyToOne
    @JoinColumn(name = "id_skinbox")
    private SkinBox skinBox;

    @OneToMany(mappedBy = "boxVenta")
    private List<ContenidoBox> ListaContenidos;

    @OneToOne(mappedBy = "boxVenta")
    private DetalleBoleta detalleBoleta;


}
