package com.example.smartapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.request.ProEditRequest;
import com.example.smartapp.core.domain.response.MyselfResponse;
import com.example.smartapp.domain.repository.ProfissionalRepository;

import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class EditarProfissionalViewModel extends ViewModel {

    private Preferences preferences;
    private ProfissionalRepository repository;

    @Inject
    public EditarProfissionalViewModel (Preferences preferences, ProfissionalRepository repository){
        this.preferences = preferences;
        this.repository = repository;
    }

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<MyselfResponse> _myself = new MutableLiveData<>();
    public LiveData<MyselfResponse> myself = _myself;

    private final MutableLiveData<Boolean> _edited = new MutableLiveData<>();
    public LiveData<Boolean> edited = _edited;

    public void loadData(){
        disposables.add(
                repository.getMyself()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> _myself.setValue(response.body()),
                                throwable -> _myself.setValue(null)
                        )
        );
    }

    public void editData(
            String completeName,
            String email,
            String phone,
            String document,
            String zipCode,
            String address,
            String number,
            String altername,
            String description
    ){
        ProEditRequest toEdit = new ProEditRequest(
                completeName,
                phone,
                email,
                document,
                altername,
                description,
                zipCode,
                address,
                number
        );
        disposables.add(
                repository.editMyself(toEdit)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> _edited.setValue(true),
                                throwable -> _edited.setValue(false)
                        )
        );
    }


    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    );
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}$"
    );
    public boolean validateField(
            String nome,
            String email,
            String telefone,
            String cpfCnpj,
            String cep,
            String endereco,
            String numero

    ){
        // Validar nome
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }

        // Validar email
        if (email == null || email.trim().isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        // Validar telefone
        String phoneDigits = telefone.replaceAll("[^0-9]", "");
        if (phoneDigits.length() < 10 || phoneDigits.length() > 11) {
            return false;
        }

        // Validar endereço
        if (endereco == null || endereco.trim().isEmpty()) {
            return false;
        }

        // Validar número
        if (numero == null || numero.trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
