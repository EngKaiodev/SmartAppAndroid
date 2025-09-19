package com.example.smartapp.core;

import android.util.Log;

import com.example.smartapp.domain.models.ErrorModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ErroHelper {

    public static <T> ErrorModel getErrorModel(Response<T> response) {
        ErrorModel retorno = new ErrorModel();

        try (ResponseBody body = response.errorBody()) {


            if (body != null) {
                JSONObject json = new JSONObject(body.string());

                String erro = json.getString("erro");
                retorno.setGeneralError(erro);

                if (json.has("erros")) {
                    JSONObject erros = json.getJSONObject("erros");
                    Iterator<String> keys = erros.keys();
                    while (keys.hasNext()) {
                        String field = keys.next();
                        String mensagem = erros.getString(field);
                        retorno.getErrors().add(mensagem);
                    }
                }

                if (json.has("mensagem")) {
                    String mensagem = json.getString("mensagem");
                    retorno.setMessage(mensagem);
                }

                if (json.has("detalhe")){
                    String detalhe = json.getString("detalhe");
                    retorno.setDetail(detalhe);
                }
            }
        } catch (IOException e) {

        } catch (JSONException e) {

        }
        return retorno;
    }
}
