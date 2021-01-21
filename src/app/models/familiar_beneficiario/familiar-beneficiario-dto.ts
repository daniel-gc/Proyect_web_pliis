import { SexoDTO } from '../generales/sexo-dto';
import { RelacionFamiliarDTO } from '../generales/relacion-familiar-dto';
export class FamiliarBeneficiarioDto {
    idFamBeneficiario:number;
    nombres: string;
    apPaterno: string;
    apMaterno: string;
    email: string;
    curp: string;
    fechaNaciomiento: string;
    sexo: SexoDTO;
    telefono: string;
    relacionFamiliar: RelacionFamiliarDTO;
    fhCreacion: Date;

    constructor() {
    }

}
