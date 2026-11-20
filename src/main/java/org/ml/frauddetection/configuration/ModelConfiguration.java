package org.ml.frauddetection.configuration;

import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import org.ml.frauddetection.Transformer.TransactionTransformer;
import org.ml.frauddetection.record.TransactionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class ModelConfiguration {

    @Bean
    public Criteria<TransactionDetails, Boolean> criteria(){
        String modelLocation = Thread.currentThread().getContextClassLoader().getResource("model.onnx").toExternalForm();
        return Criteria.builder()
                .setTypes(TransactionDetails.class, Boolean.class)
                .optModelUrls(modelLocation)
                .optTranslator(new TransactionTransformer(0.95f))
                .optEngine("OnnxRuntime")
                .build();
    }

    @Bean
    public ZooModel<TransactionDetails, Boolean> model() throws Exception {
        return criteria().loadModel();
    }

    @Bean
    public Supplier<Predictor<TransactionDetails, Boolean>> predictorProvider(ZooModel<TransactionDetails, Boolean> model){
    return model::newPredictor;
    }


}
