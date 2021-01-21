export class PanicoDTO {
    idPanico: number;
    afIdAfiliado: number;
    descripcion: string;
    // @NotNull
    esIdCentroTrabajo: number;
    fecha: Date;
    nombreDenunciado: string;
    // @NotEmpty
    tipoPanicos: string[];
    correos: string[];
    // @NotEmpty
    nombreEmpresa: string;
    // @NotEmpty
    nombreCentroTrabajo: string;

    constructor() {
    }
}
