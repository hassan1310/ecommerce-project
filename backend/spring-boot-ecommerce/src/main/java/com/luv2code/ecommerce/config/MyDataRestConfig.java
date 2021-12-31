package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager myEntityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager){
        myEntityManager=entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod [] theUnSupportedActions ={HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PUT};
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions));

        exposeIds(config);
    }

 private void exposeIds(RepositoryRestConfiguration config){
     Set<EntityType<?>> entities =myEntityManager.getMetamodel().getEntities();
     List<Class> entityClasses = new ArrayList<>();

     for (EntityType tempEntityType : entities){
         entityClasses.add(tempEntityType.getJavaType());
     }
     Class[] domainTypes = entityClasses.toArray(new Class[0]);
     config.exposeIdsFor(domainTypes);
 }
}
