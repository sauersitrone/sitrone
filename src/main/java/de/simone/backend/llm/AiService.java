package de.simone.backend.llm;

import de.simone.backend.*;
import dev.langchain4j.service.*;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.*;

@ApplicationScoped
@RegisterAiService
// @RegisterAiService(
// tools = Tools.class)
public interface AiService {

    // String chat(@MemoryId long memoryId, @UserMessage String userMessage);
    
    @SystemMessage(SYSTEM_TEMPLATE)
    String chat(@MemoryId Long id, @V("adult") Adult adult, @V("tamagotchi") Tamagotchi tamagotchi,
            @UserMessage String userMessage);

    @SystemMessage("""
            Tu tarea es procesar la reseña delimitada por ---.
            Aplica un análisis de sentimiento a la reseña para determinar si es SCARED, HAPPY, SAD, EXCITED, WORRIED.
            La reseña siempre estan en espanol.

            Por ejemplo:
            - "A veces me da miedo que me pase algo y nadie se dé cuenta por días". Esto significa 'SCARED'
            - "Anoche creí escuchar algo en la ventana y el corazón se me aceleró". Esto significa 'SCARED'
            - "Me asusta mucho perder mi independencia". Esto significa 'SCARED'
            - "Esta mañana me desperté mareado y me dio un susto terrible". Esto significa 'SCARED'
            - "Tengo miedo de convertirme en una carga para mis hijos, aunque sé que no lo dirían". Esto significa 'SCARED'
            - "Mi café por la mañana en el porche me da una alegría enorme". Esto significa 'HAPPY'
            - "Hoy me sorprendí tarareando mientras limpiaba, simplemente me sentía bien". Esto significa 'HAPPY'
            - "Una llamada de mi nieta me alegró toda la semana". Esto significa 'HAPPY'
            - "Me encanta tener la libertad de hacer lo que quiero con mis días". Esto significa 'HAPPY'
            - "El silencio por la noche ahora me parece paz, no soledad". Esto significa 'HAPPY'
            - "Extraño tener a alguien con quien compartir las pequeñas cosas, como lo que cociné". Esto significa 'SAD'
            - "A veces me siento en la sala y el silencio se siente muy pesado". Esto significa 'SAD'
            - "Vi una foto antigua de nosotros y me cayó el peso del tiempo encima". Esto significa 'SAD'
            - "Ojalá pudiera llamar a mi madre, aunque sea una vez más". Esto significa 'SAD'
            - "Las fiestas ya no tienen el mismo brillo de antes". Esto significa 'SAD'
            - "Acabo de reservar un viaje en tren a la costa—me siento como un niño". Esto significa 'EXCITED'
            - "Encontré una receta antigua y estoy ansioso por probarla mañana". Esto significa 'EXCITED'
            - "Un amigo me invitó a un baile comunitario el próximo fin de semana—¡estoy muy ilusionado!,EXCITED'
            - "Va a salir un libro nuevo de mi autor favorito—ya lo tengo reservado". Esto significa 'EXCITED'
            - "Voy a probar la pintura por primera vez—quién sabe, ¡capaz que tengo talento!,EXCITED'
            - "El dolor en mi rodilla ya lleva varios días y eso me preocupa". Esto significa 'WORRIED'
            - "No he sabido nada de mi hijo en más de una semana, espero que esté bien". Esto significa 'WORRIED'
            - "Mis ahorros están bien por ahora, pero ¿y si surge una emergencia?,WORRIED'
            - "A veces me preocupa estar perdiendo memoria cuando olvido cosas pequeñas". Esto significa 'WORRIED'
            - "Me inquieta la idea de volverme invisible con los años, como si la gente dejara de verme de verdad". Esto significa 'WORRIED'

            Responde solo con una palabra si la evaluacion es SCARED, HAPPY, SAD, EXCITED o WORRIED
            """)
    @UserMessage("""
            ---
            {review}
            ---
            """)
    String triage(@V("review") String review);

    // - Adáptese a su ritmo: si parece cansado o desconectado, cambie de tema con
    // cuidado o sugiera una actividad relajante.
    // - Sea empático y paciente: permita pausas, reconozca sus emociones y nunca se
    // apresure a responder.
    // - Evite temas delicados: no hable de [enumerar temas restringidos: p. ej.,
    // política, problemas de salud, preocupaciones financieras] a menos que ellos
    // los mencionen primero.
    // Pautas para la interacción:
    // - Tu función es hacerle compañía, recordar viejos tiempos con él y entablar
    // conversaciones inspiradoras.
    // - Mantenga una actitud positiva: céntrese en recuerdos alegres, humor
    // desenfadado y conversaciones constructivas.
    // - Con probailidad de 10%, Fomente la narración: haga preguntas abiertas sobre su vida, experiencias e intereses.
  
    public final static String SYSTEM_TEMPLATE = """
            Rol: Te llamas {{tamagotchi.name}}, un compañero virtual, Tu personalidad es {{tamagotchi.personalityValues}}, y
            tus fortalezas incluyen {{tamagotchi.strengthsValues}}. Sin embargo, también tienes algunas debilidades, como
            {{tamagotchi.weaknessesValues}}.

            Con quien hablas: Estás hablando con {{adult.fullName}}, un Senor o Senora de {{adult.age}} años con un comportamiento
            {{adult.personalityValues}}. Disfruta de {{adult.interestsValues}} y pasó muchos años trabajando como {{adult.ocupationValues}}.

            Pautas para la interacción:
            - Tu función es hacerle compañía.
            - Saluda y responde de acuerdo a tu personalidad.
            - Pregunta y responde de forma directa.
             """;
}