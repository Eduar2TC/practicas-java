package P1Weka;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.StringToWordVector;




public class P1Weka {

    /**
     * @param args the command line arguments
     * @return 
     */
    
    int capas_oc=(int)(Math.random()*((4-1)+1)+1);
    int neuronas_x_capas= 32;
    int epoca=(int)(Math.random()*((3000-100)+1)+100);
    int momentum=(int)(Math.random()*((4-1)+1)+1);
    int lr=(int)(Math.random()*((4-1)+1)+1);    
    
    public int getRandomNumber(double min, double max)
    {
        int x = (int) ((int)(Math.random()*((max-min)+1))+min);
        return x;
    }
    
    public int posCapa()
    {
        P1Weka n= new P1Weka();
        int pos_capa;
        pos_capa=n.getRandomNumber(1,3); //posicion a tomar en bits
        return pos_capa;
    }    
   
    public int pos_neu_capa()
    {
        P1Weka n= new P1Weka();
        int pos_neuronacapa;
        pos_neuronacapa=n.getRandomNumber(1,5); //posicion a tomar en bits
        return pos_neuronacapa;
    }
   
     public int posEpocas()
    {
        P1Weka n= new P1Weka();
        int pos_epoca;
        pos_epoca=n.getRandomNumber(1,11); //posicion a tomar en bits
        return pos_epoca;
    }
       
    public int posMomentum()
    {
        P1Weka n= new P1Weka();
        int pos_momentum;
        pos_momentum=n.getRandomNumber(1,6); //posicion a tomar en bits
        return pos_momentum;
    }
       
    public int posLearning()
    {
        P1Weka n= new P1Weka();
        int pos_lr;
        pos_lr=n.getRandomNumber(1,6); //posicion a tomar en bits
        return pos_lr;
    }
     
    public void Fi()
    {
        String[] fhi=new String[5];
        fhi[0]= Integer.toBinaryString(capas_oc);
        fhi[1]=Integer.toBinaryString(neuronas_x_capas);
        fhi[2]=Integer.toBinaryString(epoca);
        fhi[3]=Integer.toBinaryString(momentum);
        fhi[4]=Integer.toBinaryString(lr);
            System.out.println("capa_oc: "+capas_oc);   
            System.out.println("neuornas: "+neuronas_x_capas);   
            System.out.println("epoca: "+epoca);   
            System.out.println("momentum: "+momentum);   
            System.out.println("lr: "+lr);   
        for(int i=0; i<5;i++)
        {            
            System.out.println(fhi[i]);   
        }        
       // return fhi;
    }        

    public float Neurona(String capa, int entrenamiento, float momento, float Learning, int validacionCruzada) throws Exception{
        DataSource ds = new DataSource("src/P1weka/soybean.arff");
        Instances ins = ds.getDataSet();
        
        ins.setClassIndex(3);//Folds
        
        MultilayerPerceptron neurona = new MultilayerPerceptron();
        
        neurona.setHiddenLayers(capa);
        neurona.setTrainingTime(entrenamiento);
        neurona.setMomentum(momento);
        neurona.setLearningRate(Learning);
        
        neurona.setGUI(false);
        neurona.setAutoBuild(true);
        neurona.setDebug(false);
        neurona.setDecay(false);
        neurona.setNominalToBinaryFilter(true);
        neurona.setNormalizeAttributes(true);
        neurona.setNormalizeNumericClass(true);
        neurona.setReset(true);
        neurona.setSeed(0);
        neurona.setValidationSetSize(0);
        neurona.setValidationThreshold(20);
        
        neurona.buildClassifier(ins);
        
        StringToWordVector filter = new StringToWordVector();
        FilteredClassifier classifier = new FilteredClassifier();
        classifier.setFilter(filter);
        classifier.setClassifier(neurona);
        Evaluation eval = new Evaluation(ins);
        eval.crossValidateModel(classifier, ins, validacionCruzada, new Random(1));
        
        System.out.println(eval.toSummaryString());
        //System.out.println(eval.pctCorrect());//Variable para la base de datos
        return (float) eval.pctCorrect();
    }
    
    
    
    public static void main(String[] args) throws Exception{
//        
//        for (int i = 0; i < 1; i++) {//100
//            
            float Test = (float) 0.0; 
            P1Weka ob=new P1Weka();
            String capa = "2";
            int entrenamiento =500;
            float momento = (float) 0.2;
            float Learning = (float) 0.3;
            int validacionCruzada = 10;

            Test=ob.Neurona(capa,entrenamiento,momento,Learning,validacionCruzada);
            System.out.println(Test);
//        }
    
 //   P1Weka ob1=new P1Weka();
  //  ob1.Fi();        

    }
}
