import { Injectable } from '@angular/core';
import {ApplicationInitService} from '../application-init.service';
import {Observable} from 'rxjs';
import {CarpetaSindicalDTO} from '../../models/empresas_sindicatos/carpeta-sindical-dto';
import {HttpClient} from '@angular/common/http';
import {LogImportacionDTO} from '../../models/empresas_sindicatos/log-importacion-dto';

@Injectable({
  providedIn: 'root'
})
export class CarpetaSindicalService {

  constructor(private applicationInitService: ApplicationInitService, private httpClient: HttpClient) { }

  public getCarpetaSindical(): Observable<CarpetaSindicalDTO[]> {
      const uri = this.applicationInitService.uriPLIIS + this.applicationInitService.uriCarpetaSindical;
      return this.httpClient.get<CarpetaSindicalDTO[]> (uri);
  }


}
