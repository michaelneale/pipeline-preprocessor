package preprocessor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class PipelinePreprocessorTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PipelinePreprocessorTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PipelinePreprocessorTest.class );
    }


    public void testInsertinatorSimple() {
        PipelinePreprocessor a = new PipelinePreprocessor();
        assertEquals("yeah", a.insertinator("yeah"));
        assertEquals("stage('yeah') {",  a.insertinator("stage('yeah') {"));
        assertEquals(" stage ('yeah') { ", a.insertinator(" stage ('yeah') { "));
        assertEquals("stage('yeah') {", a.insertinator("stage 'yeah' {"));
        assertEquals("stage(faux:'yeah', woo:2) {", a.insertinator("stage faux:'yeah', woo:2   {"));
        assertEquals("timestamps {", a.insertinator("timestamps {"));
    }

    public void testFiles() throws IOException {
        PipelinePreprocessor a = new PipelinePreprocessor();
        String pipeline = "" +
                "pipeline {\n" +
                "   stage('yeah') {\n" +
                "    echo 42\n" +
                "   }" +
                "}\n";

        assertEquals(pipeline, a.preprocessPipeline(pipeline));

        String pipelineToChange = "" +
                "pipeline {\n" +
                "   stage 'yeah' {\n" +
                "    echo 42\n" +
                "   }" +
                "}\n";

        assertEquals(pipeline, a.preprocessPipeline(pipelineToChange));

        String longerPipeline = "" +
                "pipeline {\n" +
                "   stage name:'deploy', concurrency:1 {\n" +
                "    echo 42\n" +
                "    node('linux') {\n" +
                "       sh 'hey there'" +
                "    }\n" +
                "   }\n" +
                "}\n" +
                "//node do this later {" +
                "\n";

        String longerPipelineCorrect = "" +
                "pipeline {\n" +
                "   stage(name:'deploy', concurrency:1) {\n" +
                "    echo 42\n" +
                "    node('linux') {\n" +
                "       sh 'hey there'" +
                "    }\n" +
                "   }\n" +
                "}\n" +
                "//node do this later {" +
                "\n";


        assertEquals(longerPipelineCorrect, a.preprocessPipeline(longerPipeline));



    }

}
