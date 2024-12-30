package com.example.cadastro.initializer;

import com.example.cadastro.model.AreaDeAtuacao;
import com.example.cadastro.repository.AreaDeAtuacaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AreaDeAtuacaoRepository areaDeAtuacaoRepository;

    public DataInitializer(AreaDeAtuacaoRepository areaDeAtuacaoRepository) {
        this.areaDeAtuacaoRepository = areaDeAtuacaoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (areaDeAtuacaoRepository.count() == 0) { // Verifica se já existem áreas de atuação no banco
            areaDeAtuacaoRepository.saveAll(Arrays.asList(
            		new AreaDeAtuacao("Administração"),
            		new AreaDeAtuacao("Análise e Desenvolv.Sistemas"),
            		new AreaDeAtuacao("Antropologia"),
            		new AreaDeAtuacao("Arqueologia"),
            		new AreaDeAtuacao("Artes Cênicas"),
            		new AreaDeAtuacao("Artes Plásticas"),
            		new AreaDeAtuacao("Astronomia"),
            		new AreaDeAtuacao("Biologia"),
            		new AreaDeAtuacao("Biofísica"),
            		new AreaDeAtuacao("Bioquímica"),
            		new AreaDeAtuacao("Biomedicina"),
            		new AreaDeAtuacao("Biblioteconomia"),
            		new AreaDeAtuacao("Ciência da Computação"),
            		new AreaDeAtuacao("Ciências Biológicas"),
            		new AreaDeAtuacao("Ciências Contábeis"),
            		new AreaDeAtuacao("Cinema"),
            		new AreaDeAtuacao("Design"),
            		new AreaDeAtuacao("Design de Games"),
            		new AreaDeAtuacao("Design de Interiores"),
            		new AreaDeAtuacao("Design de Moda"),
            		new AreaDeAtuacao("Design de Produto"),
            		new AreaDeAtuacao("Design Gráfico"),
            		new AreaDeAtuacao("Direito"),
            		new AreaDeAtuacao("Educação Física"),
            		new AreaDeAtuacao("Educomunicação"),
            		new AreaDeAtuacao("Enfermagem"),
            		new AreaDeAtuacao("Engenharia Aeronáutica"),
            		new AreaDeAtuacao("Engenharia Agrícola"),
            		new AreaDeAtuacao("Engenharia Agronômica"),
            		new AreaDeAtuacao("Engenharia Ambiental"),
            		new AreaDeAtuacao("Engenharia Amb e Sanitária"),
            		new AreaDeAtuacao("Engenharia Biomédica"),
            		new AreaDeAtuacao("Engenharia de Alimentos"),
            		new AreaDeAtuacao("Engenharia de Bioprocessos"),
            		new AreaDeAtuacao("Engenharia de Computação"),
            		new AreaDeAtuacao("Engenharia de Ctrl e Automação"),
            		new AreaDeAtuacao("Engenharia de Energia"),
            		new AreaDeAtuacao("Engenharia de Forti e Construção"),
            		new AreaDeAtuacao("Engenharia de Materiais"),
            		new AreaDeAtuacao("Engenharia de Minas"),
            		new AreaDeAtuacao("Engenharia de Pesca"),
            		new AreaDeAtuacao("Engenharia de Petróleo"),
            		new AreaDeAtuacao("Engenharia de Produção"),
            		new AreaDeAtuacao("Engenharia de Software"),
            		new AreaDeAtuacao("Engenharia de Tel"),
            		new AreaDeAtuacao("Engenharia Eletrônica"),
            		new AreaDeAtuacao("Engenharia Elétrica"),
            		new AreaDeAtuacao("Engenharia Florestal"),
            		new AreaDeAtuacao("Engenharia Mecânica"),
            		new AreaDeAtuacao("Engenharia Mecânica e de Arm"),
            		new AreaDeAtuacao("Engenharia Metalúrgica"),
            		new AreaDeAtuacao("Engenharia Naval"),
            		new AreaDeAtuacao("Estética"),
            		new AreaDeAtuacao("Eventos"),
            		new AreaDeAtuacao("Filosofia"),
            		new AreaDeAtuacao("Física"),
            		new AreaDeAtuacao("Fisioterapia"),
            		new AreaDeAtuacao("Fonoaudiologia"),
            		new AreaDeAtuacao("Geografia"),
            		new AreaDeAtuacao("Geofísica"),
            		new AreaDeAtuacao("Gestão Ambiental"),
            		new AreaDeAtuacao("História"),
            		new AreaDeAtuacao("História da Arte"),
            		new AreaDeAtuacao("Informática Biomédica"),
            		new AreaDeAtuacao("Jornalismo"),
            		new AreaDeAtuacao("Letras"),
            		new AreaDeAtuacao("Linguística"),
            		new AreaDeAtuacao("Matemática"),
            		new AreaDeAtuacao("Medicina"),
            		new AreaDeAtuacao("Medicina Veterinária"),
            		new AreaDeAtuacao("Meteorologia"),
            		new AreaDeAtuacao("Música"),
            		new AreaDeAtuacao("Musicoterapia"),
            		new AreaDeAtuacao("Museologia"),
            		new AreaDeAtuacao("Nanotecnologia"),
            		new AreaDeAtuacao("Nutrição"),
            		new AreaDeAtuacao("Oceanografia"),
            		new AreaDeAtuacao("Odontologia"),
            		new AreaDeAtuacao("Obstetrícia"),
            		new AreaDeAtuacao("Pedagogia"),
            		new AreaDeAtuacao("Produção Cultural"),
            		new AreaDeAtuacao("Produção Editorial"),
            		new AreaDeAtuacao("Produção Multimídia"),
            		new AreaDeAtuacao("Publicidade e Propaganda"),
            		new AreaDeAtuacao("Psicologia"),
            		new AreaDeAtuacao("Radiologia"),
            		new AreaDeAtuacao("Rádio e TV"),
            		new AreaDeAtuacao("Recursos Humanos"),
            		new AreaDeAtuacao("Relações Internacionais"),
            		new AreaDeAtuacao("Relações Públicas"),
            		new AreaDeAtuacao("Saúde Coletiva"),
            		new AreaDeAtuacao("Secretariado"),
            		new AreaDeAtuacao("Serviço Social"),
            		new AreaDeAtuacao("Sociologia"),
            		new AreaDeAtuacao("Sistemas de Informação"),
            		new AreaDeAtuacao("Teatro"),
            		new AreaDeAtuacao("Teologia"),
            		new AreaDeAtuacao("Turismo"),
            		new AreaDeAtuacao("Zootecnia"),
            		new AreaDeAtuacao("Agronomia"),
            		new AreaDeAtuacao("Biotecnologia"),
            		new AreaDeAtuacao("Ecologia"),
            		new AreaDeAtuacao("Engenharia Ambiental"),
            		new AreaDeAtuacao("Engenharia de Pesca"),
            		new AreaDeAtuacao("Engenharia Hídrica"),
            		new AreaDeAtuacao("Gestão Ambiental"),
            		new AreaDeAtuacao("Geologia"),
            		new AreaDeAtuacao("Oceanografia"),
            		new AreaDeAtuacao("Engenharia Agrícola"),
            		new AreaDeAtuacao("Engenharia de Energia"),
            		new AreaDeAtuacao("Engenharia Florestal"),
            		new AreaDeAtuacao("Medicina Veterinária"),
            		new AreaDeAtuacao("Meteorologia"),
            		new AreaDeAtuacao("Zootecnia")

            ));
        }
    }
}
