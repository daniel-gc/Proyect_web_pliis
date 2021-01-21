import {TipoArchivoDTO} from './tipo-archivo-dto';

export class ArchivoCreditoDTO {
    idCredito: number;
    nombre: string;
    extencion: string;
    archivo: string;
    idTipoArchivo: number;
    tipoArchivoDTO: TipoArchivoDTO;
    botonDisabled1: boolean;
    botonDisabled2: boolean;
    botonDisabled3: boolean;

    constructor() {
    }
}
