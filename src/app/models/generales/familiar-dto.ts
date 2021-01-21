import {RelacionFamiliarDTO} from './relacion-familiar-dto';

export class FamiliarDTO {
    idVinculoFamiliar: number;
    idFamiliar: number;
    nombres: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    relacionFamiliarDTO: RelacionFamiliarDTO;
    email: string;

    constructor() {
    }
}
