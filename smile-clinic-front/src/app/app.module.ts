import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { TestingBackComponent } from './testing-back/testing-back.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  declarations: [AppComponent, TestingBackComponent],
  providers: [],
  exports: [HttpClientModule],
  bootstrap: [AppComponent],
})
export class AppModule {}
