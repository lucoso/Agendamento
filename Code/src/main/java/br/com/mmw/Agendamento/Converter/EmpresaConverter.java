package br.com.mmw.Agendamento.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.mmw.Agendamento.Manager.EmpresaClienteManager;
import br.com.mmw.Agendamento.Model.EmpresaCliente;

@FacesConverter("empresaConverter")
public class EmpresaConverter implements Converter {
							
	EmpresaClienteManager ecm = new EmpresaClienteManager();
							
			public Object getAsObject(FacesContext context, UIComponent component, String value) {
				EmpresaCliente e = ecm.BuscarEmpresaPorIDConverter(Long.parseLong(value));
						return e;
			}

			public String getAsString(FacesContext context, UIComponent component, Object value) {
				EmpresaCliente e = (EmpresaCliente) value;
						return String.valueOf(e.getId());
			}
}


