package https.github.com.FrancoBorba.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

// JsonSerializer<String>: Cria uma Serializer personalizado do tipo String
public class GenderSerializer extends JsonSerializer<String> {

  @Override
  public void serialize(String gender, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
    // TODO Auto-generated method stub
    String formatedGender = "Male".equals(gender) ? "M" : "F";
    // Formata o JSON do gender , se for Male transforma em M se não transforma em F
    arg1.writeString(formatedGender);
  }

  // Esta classe foi feita para o estudo de JSON serializates , porem a seção foi apenas para estudos e não sera seguida durante o curso , pórem deixarie esta classe já que não afeta o resto do código com algumas anotações de estudo tbm 

  // Ps as anotações não tem haver com o que a classe faz que é apenas personalizar um JSON

  /*
  Manipulação de JSON no Jackson

  O Jackson é uma biblioteca popular para manipulação de JSON em Java, permitindo serialização (converter objetos Java em JSON) e desserialização (converter JSON em objetos Java

  1. Serialização e Desserialização
    Serialização: Converter um objeto Java em JSON.

    Desserialização: Converter um JSON em um objeto Java.
  
  Principais Anotações do Jackson
    1. @JsonProperty – Definir nome do campo no JSON
    Permite mapear um nome específico para um campo no JSON.

    2. @JsonPropertyOrder – Definir a ordem dos campos

    3. @JsonIgnore – Omitir um campo

    4. @JsonInclude – Omitir valores nulos ou padrão
                    - NON_EMPTY: Ignora nulos e coleções vazias ("", [], {})
                    -  NON_DEFAULT: Ignora valores padrão (false, 0, null)
  
    5. @JsonFormat – Definir formato de datas

    6. @JsonSerialize e @JsonDeserialize – Personalizar a conversão
   */
  
}
