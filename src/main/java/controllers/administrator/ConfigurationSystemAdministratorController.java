package controllers.administrator;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationSystemService;
import domain.ConfigurationSystem;

@Controller
@RequestMapping("/configurationSystem/administrator")
public class ConfigurationSystemAdministratorController {


		// Services ---------------------------------------

		@Autowired
		private ConfigurationSystemService configurationSystemService;

		// Constructors -----------------------------------------------------

		public ConfigurationSystemAdministratorController() {
			super();
		}
		
		// Create methods---------------------------

		// Edition------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit() {
			ModelAndView result;
			Collection<ConfigurationSystem> configurationSystemc;
			ConfigurationSystem configurationSystem=null;

			configurationSystemc = configurationSystemService.findAll();
			for(ConfigurationSystem c:configurationSystemc){
				configurationSystem=c;
			}
			Assert.notNull(configurationSystem);
			result = createEditModelAndView(configurationSystem);

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid ConfigurationSystem configurationSystem, BindingResult binding) {
			ModelAndView result;
			if (binding.hasErrors()) {
				result = createEditModelAndView(configurationSystem);
			} else {
				try {
					configurationSystemService.save(configurationSystem);
					String j="redirect:edit.do";
					result = new ModelAndView(j);
				} catch (Throwable oops) {
					result = createEditModelAndView(configurationSystem,
							"configurationSystem.commit.error");
				}
			}
			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(ConfigurationSystem configurationSystem, BindingResult binding) {
			ModelAndView result;

			try {
				configurationSystemService.delete(configurationSystem);
				String j="redirect:edit.do";
				result = new ModelAndView(j);
			} catch (Throwable oops) {
				result = createEditModelAndView(configurationSystem, "configurationSystem.commit.error");

			}
			return result;
		}

		// Ancillary methods------------------------------

		protected ModelAndView createEditModelAndView(ConfigurationSystem configurationSystem) {
			ModelAndView result;

			result = createEditModelAndView(configurationSystem, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(ConfigurationSystem configurationSystem,
				String message) {
			ModelAndView result;

			result = new ModelAndView("configurationSystem/edit");
			result.addObject("configurationSystem", configurationSystem);
			result.addObject("message", message);

			return result;
		}

	}

