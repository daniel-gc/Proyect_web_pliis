export class NuevoUsuarioArquitecturaDTO {
    nombres: string;
    passw: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    email: string;
    nick: string;
    roles: string[];
    esIdCentroTrabajo: number;
    esIdSindicato: number;
    fotoCredencial: [];
    direccionDomicilio: string;
    rfc: string;
    curp: string;
    lugarNacimiento: string;
    fechaAfiliacion: Date;
    fotoAfiliado: [];
    fechaNacimiento: Date;
    contrato: string;
    fechaRegistro: Date;
    fechaBaja: Date;
    nombreSindicato: string;
    nombrePuestoTrabajo: string;
    salarioMensualNeto: number;
    salarioMensualBruto: number;
    deseaAfiliarse: boolean;
    fechaIngresoEmpresa: Date;
    idEstadoCivil: number;
    idNacionalidad: number;
    idSexo: number;
    telefono: string;
    calle: string;
    numero: string;
    colonia: string;
    alcaldia: string;
    ciudad: string
    pais: string;

    constructor() {
    }
}
