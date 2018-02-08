package com.revature.caliber.swagger;

import java.util.ArrayList;
import java.util.List;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

public class DocumentationController implements SwaggerResourcesProvider{
	
	@Override
    public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		resources.add(swaggerResource("GradeRepositoryService", "/api/grade-controller/v2/api-docs", "2.0"));
		//resources.add(swaggerResource("account-service", "/api/account/v2/api-docs", "2.0"));
		return resources;
	}
	
	private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
