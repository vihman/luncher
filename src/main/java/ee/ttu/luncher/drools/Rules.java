package ee.ttu.luncher.drools;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.logging.Level;

@Log
public class Rules {
	
	private @Setter @Getter Integer step = 0;
	private @Setter @Getter String Question;
	private @Setter @Getter Map <String, Integer> choices;
	
	private FactDao factDao;
	
	protected KieSession kSession;
	
	public class FormStrings {
		public final int ASIZE = 9;
		@Getter @Setter private String question;
		@Getter @Setter private String[] answers;
		
		private final String[] qs = {
				"Kas söömiseks kuluv aeg on oluline?",
				"Soovite süüa koha peal või kuskil mujal?",
				"Kas teeninduse kvaliteet on oluline?",
				"Kas 15 eurot pearoa eeson liiga kallis?",
				"Kas liha söömine tekitab teis negatiivseid tundeid?",
				"Kas sooviksite laua varem reserveerida?",
				"Kas olulisem on elamus või kõhutäis?",
				"Kas olete valmis ennast viisakalt riidesse panema?",
				"Kas toidu kvaliteet on teile oluline?",
				"Kas lähete autoga?" };

		private final String[][] as = { 
				{ "jah", "ei" },
				{ "koha peal", "kaasa"},
				{ "oluline", "ei ole oluline"},
				{ "kallis", "ei ole kallis"},
				{ "jah", "ei" },
				{ "jah", "ei" },
				{ "elamus", "kõhutäis" },
				{ "jah", "ei" },
				{ "jah", "ei" },
				{ "jah", "ei" }
				};
		
		public FormStrings(int i) {
			if (ASIZE > i) {
				question = qs[i];
				answers = as[i];				
			} else {
				question = qs[0];
				answers = as[0];				
			}

		}
	}
	public Rules() {
		try {

			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			
			kSession = kContainer.newKieSession("ksession-rules");
			factDao = new FactDao();
			factDao.load();
			for (FactVo fact : factDao.getFacts()) {
				log.info("trying to load into drools:" + fact.toString());
				kSession.insert(fact);
			}

			// TODO: fill decisiontree and launch rules

			/*Message message = new Message();
			/message.setMessage("Hello World");
		    message.setStatus(Message.HELLO);
			kSession.insert(message);
			kSession.fireAllRules();*/
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void processAnswer(Answer answer) {
		return;
	}
	
	public void launch() {
		kSession.fireAllRules();
		log.info("and now the remainers:");
		for (Object fact : kSession.getObjects()) {
			log.info(fact.toString());
		}
	}
	
	public void increaseStep() {
		step += 1;
	}
	
	
	public FormStrings getFormStrings() {
		return new FormStrings(step);
	}
}
