package com.example.healthymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class registropaciente extends AppCompatActivity {
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    EditText nombre, apellido, curp, fechanac, tel, email, pass, confirmpass;
    Button registrar;
    Spinner genero;
    String[] opgenero={"Femenino", "Masculino", "Otro"};
    String generoact = "";
    final Calendar myCalendar= Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registropaciente);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        registrar=(Button)findViewById(R.id.registerbtn);
        nombre = (EditText) findViewById(R.id.setnombre);
        apellido =(EditText) findViewById(R.id.setapellidos);
        curp=(EditText)findViewById(R.id.curp);
        tel =(EditText) findViewById(R.id.set_telefono);
        genero =(Spinner)findViewById(R.id.setgenero);
        email=(EditText) findViewById(R.id.setemail);
        fechanac=(EditText) findViewById(R.id.setfecha);
        pass=(EditText) findViewById(R.id.password);
        confirmpass = (EditText)findViewById(R.id.confirmpassword);
        nombre.setError(null);
        apellido.setError(null);
        curp.setError(null);
        tel.setError(null);
        email.setError(null);
        fechanac.setError(null);
        pass.setError(null);
        confirmpass.setError(null);
        final String regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";
        final String regexpass = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,63}$";
        //final String regexcurp ="([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$/";
        final String regexcurp ="^[A-Z]{4}[0-9]{6}[HM]{1}[A-Z]{6}[0-9]{1}$";
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                registropaciente.this, android.R.layout.simple_dropdown_item_1line, opgenero);
        genero.setAdapter(aa);
        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                generoact = opgenero[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString().trim();
                String ape = apellido.getText().toString().trim();
                String curpp = curp.getText().toString().trim();
                String correo = email.getText().toString().trim();
                String tele = tel.getText().toString().trim();
                String fecha = fechanac.getText().toString().trim();
                String pswd = pass.getText().toString().trim();
                String confpswd = confirmpass.getText().toString().trim();
                if (nom.isEmpty() && ape.isEmpty() &&curpp.isEmpty() && correo.isEmpty() && tele.isEmpty() && fecha.isEmpty() && pswd.isEmpty() && confpswd.isEmpty()){
                  Toast.makeText(registropaciente.this, "Debes acompletar todos los campos!", Toast.LENGTH_SHORT).show();}
                if (!curpp.matches(regexcurp)) {
                   curp.setError("Ingresa una curp valida");
                   curp.requestFocus();

               } else if(tele.length()>10 || tele.length()<10){
                   tel.setError("Ingresa un numero de telefono de 10 digitos");
                   tel.requestFocus();

               }else if( !correo.matches(regex)){
                    email.setError("Ingresa un correo valido");
                    email.requestFocus();
                    return;
                }  else if (!pswd.matches(regexpass)) {
                    pass.setError("Ingresa una contraseña valida con 8 caracteres, numeros y letras mayusculas...");
                    pass.requestFocus();

                } else if (!pswd.equals(confpswd)) {
                    pass.setError("Las contraseñas no coinciden.");
                    pass.requestFocus();
                    confirmpass.setError("Las contraseñas no coinciden.");
                    confirmpass.requestFocus();

                }
               //FUNCION PARA REGISTRAR EL USUARIO EN AUTH FIREBASE
               mAuth.createUserWithEmailAndPassword(email.getText().toString(), confirmpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //AQUI VA LA FUNCION PARA HACER EL REGISTRO DE LOS DEMAS DATOS A LA TABLA PACIENTES
                        Map<String,Object> map =new HashMap<>();
                        map.put("nombres", nom);
                        map.put("apellidos", ape);
                        map.put("email",correo);
                        map.put("fechanacimiento",fecha);
                        map.put("telefono",tele);
                        map.put("sexo",generoact);
                        mFirestore.collection("users-paciente").document(curpp).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                startActivity(new Intent( registropaciente.this, login_paciente.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(registropaciente.this, " NO exitoso", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(registropaciente.this, "exitoso", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registropaciente.this, " NO exitoso", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        fechanac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(registropaciente.this, date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        fechanac.setText(dateFormat.format(myCalendar.getTime()));
    }

}