import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../breadcrumb.service';

@Component({
  selector: 'app-serv-funerarios',
  templateUrl: './serv-funerarios.component.html',
  styleUrls: ['./serv-funerarios.component.css']
})
export class ServFunerariosComponent implements OnInit {
    // empresas: Empresa[];
    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Beneficios/Servicios Funerarios', routerLink: ['/beneficios/serviciosFunerarios']}
        ]);
    }

  ngOnInit() {

  }

}
