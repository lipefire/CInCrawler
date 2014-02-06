package br.ufpe.cin.interno.saida;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufpe.cin.interno.config.Propriedades;
import br.ufpe.cin.interno.config.Valores;
import br.ufpe.cin.interno.entidade.Professor;

public class Json implements ISaida {

	@Override
	public void imprimir(List<Professor> professores) {
		JSONArray json = new JSONArray();

		for (Professor p : professores) {
			JSONObject profJson = new JSONObject();

			try {
				profJson.put(Valores.nome.name(), p.getNome());
				profJson.put(Valores.email.name(), p.getEmail());
				profJson.put(Valores.lattes.name(), p.getLattes());
				profJson.put(Valores.sala.name(), p.getSala());
				profJson.put(Valores.pagina.name(), p.getPagina());
				profJson.put(Valores.fone.name(), p.getFone());
				profJson.put(Valores.fax.name(), p.getFax());

				JSONArray areasJson = new JSONArray();
				for (String area : p.getAreasInteresse()) {
					JSONObject areaJson = new JSONObject();
					areaJson.put(Valores.area.name(), area);
					areasJson.put(areaJson);
				}

				profJson.put(Valores.areas.name(), areasJson);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			json.put(profJson);

		}

		try {

			String nomeArquivo = Propriedades
					.lerPropriedade(Valores.nomeArquivoSaida.name());
			FileWriter file = new FileWriter(nomeArquivo + ".json");
			json.write(file);
			file.flush();
			file.close();
			System.out.println("Arquivo JSON escrito com �xito!");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
