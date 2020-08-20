package br.com.mmw.Agendamento.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Model.Funcionario;

@FacesConverter("funcionarioConverter")
public class FuncionarioConverter implements Converter {
						
			FuncionarioManager fm = new FuncionarioManager();
						
				public Object getAsObject(FacesContext context, UIComponent component, String value) {
					Funcionario f = fm.BuscarFuncionarioPorIDConverter(Long.parseLong(value));
							return f;
						}

						public String getAsString(FacesContext context, UIComponent component, Object value) {
							Funcionario f = (Funcionario) value;
							return String.valueOf(f.getId());
						}
			}




