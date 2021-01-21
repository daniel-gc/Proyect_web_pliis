import {MesDTO} from './mes-dto';

export class TarjetaDTO {
    numeroTarjeta: string;
    nombreTarjeta: string;
    mesExpira: MesDTO;
    anioExpira: number;
    anioCorto: string;
    ccv: string;
    srcImagen: string;

    constructor() {
        this.numeroTarjeta = '';
        this.nombreTarjeta = '';
        this.mesExpira = null;
        this.anioExpira = -1;
        this.anioCorto = '';
        this.ccv = null;
        this.srcImagen = '';
    }
}
