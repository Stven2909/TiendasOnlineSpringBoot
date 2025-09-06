import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { ProductoService } from './app/producto.service'; 
import { provideHttpClient } from '@angular/common/http'; 
import { provideRouter } from '@angular/router'; 
import { routes } from './app/app.routes'; 

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    ProductoService,
    provideRouter(routes) 
  ]
}).catch(err => console.error(err));
