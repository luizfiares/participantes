import { Injectable } from '@angular/core';
import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable';
import { DatePipe } from '@angular/common';
import * as XLSX from 'xlsx';  

@Injectable({
  providedIn: 'root'
})
export class RelatorioService {

  constructor(private datePipe: DatePipe) { }

  formatarCPF(cpf: string): string {
    if (!cpf) return '';
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }

  formatarDataNascimento(data: string): string {
    return this.datePipe.transform(data, 'dd/MM/yyyy') || '';
  }

  formatarCelular(celular: string): string {
    if (!celular) return '';
    const celularFormatado = celular.replace(/\D/g, '');
    if (celularFormatado.length === 11) {
      return `(${celularFormatado.substring(0, 2)}) ${celularFormatado.substring(2, 7)}-${celularFormatado.substring(7)}`;
    }
    return celular; 
  }


  imprimirRelatorioPdf(participantes: any[]) {
    const doc = new jsPDF();
    const dataEmissao = new Date();
    const dataFormatada = `${dataEmissao.getDate().toString().padStart(2, '0')}/${(dataEmissao.getMonth() + 1).toString().padStart(2, '0')}/${dataEmissao.getFullYear()}`;
    doc.setFontSize(8);
    doc.text(`Emissão: ${dataFormatada}`, 14, 15);
  
    const title = 'Relatório de Candidatos';
    const pageWidth = doc.internal.pageSize.width;
    const textWidth = doc.getStringUnitWidth(title) * doc.getFontSize() / doc.internal.scaleFactor;
    const xPosition = (pageWidth - textWidth) / 2;
    doc.setFontSize(9);  
    doc.text(title, xPosition, 15);
  

    const columns = ['Nome', 'CPF',  'Gênero', 'Nascimento', 'Celular','Email', 'Local Trabalho', 'Área de Atuação', 'Grau de Instrução', 'Ativo'];
    const rows = participantes.map(candidato => [
      candidato.nome || '', 
      this.formatarCPF(candidato.cpf) || '', 
      candidato.genero || '', 
      this.formatarDataNascimento(candidato.dataNascimento) || '',  
      this.formatarCelular(candidato.celular) || '', 
      candidato.email || '', 
      candidato.estado?.nome || '', 
      candidato.areaDeAtuacao?.nome || '', 
      candidato.nivelDeEscolaridade?.nome || '', 
      candidato.ativo ? 'Ativo' : 'Inativo' 
    ]);
  
    autoTable(doc, {
      startY: 25,  
      head: [columns],  
      body: rows,  
      margin: { top: 30, left: 10, right: 10, bottom: 10 },  
      styles: {
        fontSize: 6,  
        cellPadding: 1,  
        halign: 'left',  
        valign: 'middle',  
        lineWidth: 0.1,  
        lineColor: [0, 0, 0],  
      },
      headStyles: {
        fillColor: [0, 123, 255],  
        textColor: 255,  
        fontStyle: 'bold',  
      },
      bodyStyles: {
        fillColor: [255, 255, 255],  
        textColor: [0, 0, 0],  
      },
      alternateRowStyles: {
        fillColor: [240, 240, 240],  
      },
      columnStyles: {
        0: { cellWidth: 'auto' },  
        1: { cellWidth: 'auto' },
        2: { cellWidth: 'auto' },
        3: { cellWidth: 'auto' },
        4: { cellWidth: 'auto' },
        5: { cellWidth: 'auto' },
        6: { cellWidth: 'auto' },
        7: { cellWidth: 'auto' },
        8: { cellWidth: 'auto' },
        9: { cellWidth: 'auto' }
      }
    });
  
    const nomeArquivo = `relatorio_candidatos_${dataFormatada}.pdf`;
    doc.save(nomeArquivo);
  }


  imprimirRelatorioXls(participantes: any[]) {
    const dataEmissao = new Date();
    const dataFormatada = `${dataEmissao.getDate().toString().padStart(2, '0')}/${(dataEmissao.getMonth() + 1).toString().padStart(2, '0')}/${dataEmissao.getFullYear()}`;

    const title = 'Relatório de Candidatos';
    const columns = ['Nome', 'CPF', 'Gênero', 'Nascimento', 'Celular','Email', 'Local Trabalho', 'Área de Atuação', 'Grau de Instrução', 'Ativo'];
    const rows = participantes.map(candidato => [
      candidato.nome || '', 
      this.formatarCPF(candidato.cpf) || '', 
      candidato.genero || '', 
      this.formatarDataNascimento(candidato.dataNascimento) || '',  
      this.formatarCelular(candidato.celular) || '',
      candidato.email || '',  
      candidato.estado?.nome || '', 
      candidato.areaDeAtuacao?.nome || '', 
      candidato.nivelDeEscolaridade?.nome || '', 
      candidato.ativo ? 'Ativo' : 'Inativo' 
    ]);


    const ws: XLSX.WorkSheet = XLSX.utils.aoa_to_sheet([columns, ...rows]);


    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Candidatos');


    const nomeArquivo = `relatorio_candidatos_${dataFormatada}.xlsx`;

    // Exportar para Excel
    XLSX.writeFile(wb, nomeArquivo);
}
}
