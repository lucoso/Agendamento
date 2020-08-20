package br.com.mmw.Agendamento.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.mmw.Agendamento.Manager.ClienteManager;
import br.com.mmw.Agendamento.Model.Cliente;

@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {
					
		ClienteManager cm = new ClienteManager();
					
			public Object getAsObject(FacesContext context, UIComponent component, String value) {
				Cliente c = cm.BuscarClientePorIDConverter(Long.parseLong(value));
						return c;
					}

					public String getAsString(FacesContext context, UIComponent component, Object value) {
						Cliente c = (Cliente) value;
						return String.valueOf(c.getId());
					}
		}

