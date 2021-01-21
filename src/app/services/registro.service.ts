import { Injectable } from '@angular/core';
import {ApplicationInitService} from './application-init.service';
import {Observable} from 'rxjs';
import {EmpresaDTO} from '../models/generales/empresa-dto';
import {EmpleadoDTO} from '../models/generales/empleado-dto';
import {HttpClient} from '@angular/common/http';
import {SexoDTO} from '../models/generales/sexo-dto';
import {NacionalidadDTO} from '../models/generales/nacionalidad-dto';
import {EstadoCivilDTO} from '../models/generales/estado-civil-dto';
import {NuevoUsuarioArquitecturaDTO} from '../models/generales/nuevo-usuario-arquitectura-dto';



@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  constructor(private applicationInitService: ApplicationInitService,
              private httpClient: HttpClient) { }

  public getUsuario(idEmpresa: number, numeroEmpleado: string): Observable<any> {
      return this.applicationInitService.getExisteEmpleado(idEmpresa, numeroEmpleado);
  }
  public getCatalogoSexo(): Observable<SexoDTO[]> {
      return this.applicationInitService.getCatalogoSexo();
  }
  public getCatalogoNacionalidad(): Observable<NacionalidadDTO[]> {
      return this.applicationInitService.getCatalogoNacionalidad();
  }
  public getCatalogoEstadoCivil(): Observable<EstadoCivilDTO[]> {
      return this.applicationInitService.getCatalogoEstadoCivil();
  }

}
