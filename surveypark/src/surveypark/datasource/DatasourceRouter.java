package surveypark.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DatasourceRouter extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		SurveyToken token=SurveyToken.getCurrentToken();
		if(token!=null){
			Integer id=token.getCurrentSurvey().getId();
			//解除绑定
			SurveyToken.unbindCurrentToken();
			return (id%2)==0?"even":"odd";
		}
		
		return null;
	}

}
