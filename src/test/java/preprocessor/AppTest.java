package preprocessor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue( true );
    }

    public void testInsertinatorSimple() {
        App a = new App();
        assertEquals("yeah", a.insertinator("yeah"));
        assertEquals("stage('yeah') {", a.insertinator("stage('yeah') {"));
        assertEquals(" stage ('yeah') { ", a.insertinator(" stage ('yeah') { "));
        assertEquals("stage('yeah') {", a.insertinator("stage 'yeah' {"));
        assertEquals("stage(faux:'yeah', woo:2) {", a.insertinator("stage faux:'yeah', woo:2   {"));
        assertEquals("timestamps {", a.insertinator("timestamps {"));
    }

}
