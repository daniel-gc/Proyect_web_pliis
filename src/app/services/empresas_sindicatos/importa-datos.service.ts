import { Injectable } from '@angular/core';
import {ApplicationInitService} from '../application-init.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LogImportacionDTO} from '../../models/empresas_sindicatos/log-importacion-dto';
import {BreadcrumbService} from '../../breadcrumb.service';

@Injectable({
  providedIn: 'root'
})
export class ImportaDatosService {

  constructor(private applicationInitService: ApplicationInitService, private httpClient: HttpClient) {  }

    public getImportaDatos(): Observable<LogImportacionDTO[]> {
        const uri = this.applicationInitService.uriPLIIS + this.applicationInitService.uriImportaDatos;
        return this.httpClient.get<LogImportacionDTO[]> (uri);
    }
}
