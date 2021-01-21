import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-llamadas',
  templateUrl: './llamadas.component.html',
  styleUrls: ['./llamadas.component.css']
})
export class LlamadasComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Beneficios/Llamadas Ilimitadas', routerLink: ['/beneficios/llamadasIlimitadas']}
        ]);
    }

  ngOnInit() {
  }

}
