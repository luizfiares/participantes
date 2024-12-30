
import { AreaDeAtuacao } from "./areaDeAtuacao.models";
import { EnderecoParticipante } from "./enderecoParticipante.models";
import { Estados } from "./estados.models";
import { NivelDeEscolaridade } from "./niveDelEscolaridade.models";
export interface Usuario {
    id?: number;      
    nome: string;     
    email: string;    
    curriculo: Blob;  
    cpf: '',
    genero: 'Masculino',
    dataNascimento: '',
    celular: '',
    ativo: false,
    areaDeAtuacao?: AreaDeAtuacao;
    nivelDeEscolaridade?: NivelDeEscolaridade;
    estado?: Estados;
    enderecoParticipante?: EnderecoParticipante;
  }