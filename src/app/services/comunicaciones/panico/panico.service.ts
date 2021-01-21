import { Injectable } from '@angular/core';
import {ApplicationInitService} from '../../application-init.service';
import {HttpClient} from '@angular/common/http';
import {NuevoUsuarioArquitecturaDTO} from '../../../models/generales/nuevo-usuario-arquitectura-dto';
import {Observable} from 'rxjs';
import {MensajeDTO} from '../../../models/generales/mensaje-dto';
import {PanicoDTO} from '../../../models/panico/panico-dto';

const uriCrearPanico = '/comunicaciones/panico/crear';
const uriPLIIS = 'https://pliis.mx:8443/PLIIS';
// const uriPLIIS = 'http://localhost:8080/PLIIS'; // 3.130.112.216
// const uriPLIIS = 'http://3.130.112.216:8080/PLIIS';

@Injectable({
  providedIn: 'root'
})
export class PanicoService {

  constructor(private applicationInitService: ApplicationInitService,
              private httpClient: HttpClient) { }

    public crearPanico(panicoDTO: PanicoDTO): Observable<any> {
      const url = this.applicationInitService.uriPLIIS + this.applicationInitService.uriCrearPanico;
      return this.httpClient.post<any>(url, panicoDTO);
    }
}
