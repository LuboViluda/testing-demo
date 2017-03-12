import org.mockito.exceptions.misusing.CannotVerifyStubOnlyMock;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Created by lubomir.viluda on 3/12/2017.
 */
public class MeaningOfLifeTest {

    @Test
    public void computeMeaning_withoutSettingPrimitiveValue_returnZero() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);

        final int result = meaningOfLife.computeMeaning();

        assertEquals(result, 0);
    }

    @Test
    public void getEmptyObject_mockWithoutSettingObject_returnNull() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);

        final java.lang.Object result = meaningOfLife.getEmptyObject();

        assertNull(result);
    }

    @Test
    public void getMeaningOfLife_doReturnWhenMultipleValues() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);
        doReturn(1).doReturn(2).doReturn(42).when(meaningOfLife).computeMeaning();

        final int result1 = meaningOfLife.computeMeaning();
        final int result2 = meaningOfLife.computeMeaning();
        final int result3 = meaningOfLife.computeMeaning();

        assertEquals(result1, 1);
        assertEquals(result2, 2);
        assertEquals(result3, 42);
    }

    @Test
    public void getMeaningOfLife_whenThenReturnMultipleValues() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);
        when(meaningOfLife.computeMeaning()).thenReturn(1).thenReturn(2).thenReturn(42);

        final int result1 = meaningOfLife.computeMeaning();
        final int result2 = meaningOfLife.computeMeaning();
        final int result3 = meaningOfLife.computeMeaning();

        assertEquals(result1, 1);
        assertEquals(result2, 2);
        assertEquals(result3, 42);
    }

    @Test
    public void getMeaningOfLife_whenThenNicerSyntaxReturnMultipleValuesStubbing() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);
        when(meaningOfLife.computeMeaning()).thenReturn( 1, 2, 42);

        final int result1 = meaningOfLife.computeMeaning();
        final int result2 = meaningOfLife.computeMeaning();
        final int result3 = meaningOfLife.computeMeaning();

        assertEquals(result1, 1);
        assertEquals(result2, 2);
        assertEquals(result3, 42);
    }

    @Test
    public void getMeaningOfLife_multipleValuesStubbing_lastValuePreserve() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);
        doReturn(1).doReturn(2).doReturn(42).when(meaningOfLife).computeMeaning();

        meaningOfLife.computeMeaning();
        meaningOfLife.computeMeaning();
        final int result3 = meaningOfLife.computeMeaning();
        final int result4 = meaningOfLife.computeMeaning();

        assertEquals(result3, 42);
        assertEquals(result4, 42);
    }

    @Test
    public void getMeaningOfLifeInstance_spyWithoutSettingValue_returnObject() throws Exception {
        MeaningOfLife meaningOfLife = new MeaningOfLife();
        MeaningOfLife spyMeaningOfLife = spy(meaningOfLife);

        final Object result = spyMeaningOfLife.getEmptyObject();

        assertNotNull(result);
    }

    @Test
    public void computeMeaning_mockDoReturn_noCallToRealImplementation() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);
        doReturn(42).when(meaningOfLife).computeMeaning();

        meaningOfLife.computeMeaning();
    }

    @Test
    public void computeMeaning_mockWithThenReturn_noCallToRealImplementation() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);
        when(meaningOfLife.computeMeaning()).thenReturn(42);

        meaningOfLife.computeMeaning();
    }

    @Test
    public void computeMeaning_spyDoReturn_noCallRealImplementation() throws Exception {
        MeaningOfLife meaningOfLife = new MeaningOfLife();
        MeaningOfLife meaningOfLifeSpy = spy(meaningOfLife);
        doReturn(42).when(meaningOfLifeSpy).computeMeaning();

        meaningOfLifeSpy.computeMeaning();
    }

    @Test(expectedExceptions = LifeHasNoMeaningException.class)
    public void computeMeaning_spyWithThenReturn_callRealImplementation() throws Exception {
        MeaningOfLife meaningOfLife = new MeaningOfLife();
        MeaningOfLife meaningOfLifeSpy = spy(meaningOfLife);
        when(meaningOfLifeSpy.computeMeaning()).thenReturn(42);

        meaningOfLifeSpy.computeMeaning();
    }

    @Test(expectedExceptions = LifeHasNoMeaningException.class)
    public void computeMeaning_mockCallRealMethod_callRealImplementation() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class);
        doCallRealMethod().when(meaningOfLife).computeMeaning();

        meaningOfLife.computeMeaning();
    }

    @Test(expectedExceptions = LifeHasNoMeaningException.class)
    public void callComputeMeaning_spyWithThenReturn_noCallToRealImplementation() throws Exception {
        MeaningOfLife meaningOfLife = new MeaningOfLife();
        MeaningOfLife meaningOfLifeSpy = spy(meaningOfLife);
        doReturn(42).when(meaningOfLifeSpy).callComputeMeaning();

        final int result = meaningOfLife.callComputeMeaning();

        assertEquals(result, 42);
    }

    @Test(expectedExceptions = LifeHasNoMeaningException.class)
    public void callComputeMeaning_spyDoReturn_callRealImplementation() throws Exception {
        MeaningOfLife meaningOfLife = new MeaningOfLife();
        MeaningOfLife meaningOfLifeSpy = spy(meaningOfLife);
        when(meaningOfLifeSpy.callComputeMeaning()).thenReturn(42);

        meaningOfLife.callComputeMeaning();
    }

    @Test(expectedExceptions = LifeHasNoMeaningException.class)
    public void callComputeMeaning_mockFunctionCalledInsideFunctionCalledInTest_noEffectExceptionThrown() throws Exception {
        MeaningOfLife meaningOfLife = new MeaningOfLife();
        MeaningOfLife meaningOfLifeSpy = spy(meaningOfLife);
        doReturn(20).when(meaningOfLifeSpy).computeMeaning();
        when(meaningOfLifeSpy.callComputeMeaning()).thenReturn(42);

        meaningOfLife.callComputeMeaning();
    }

    @Test
    public void incrementCounter_operationOverSpy_NoEffectToOriginalObject() throws Exception {
        MeaningOfLife meaningOfLife = new MeaningOfLife();
        MeaningOfLife meaningOfLifeSpy = spy(meaningOfLife);
        doCallRealMethod().when(meaningOfLifeSpy).incrementCounter();

        meaningOfLifeSpy.incrementCounter();
        meaningOfLifeSpy.incrementCounter();

        assertEquals(meaningOfLifeSpy.getCounter(), 2);
        assertEquals(meaningOfLife.getCounter(), 0);

    }

    @Test(expectedExceptions = CannotVerifyStubOnlyMock.class)
    public void computeMeaning_verifyOnStub_thrownException() throws Exception {
        MeaningOfLife meaningOfLife = mock(MeaningOfLife.class, withSettings().stubOnly());

        meaningOfLife.computeMeaning();

        // no assertion against stub :)
        verify(meaningOfLife, times(1)).computeMeaning();
    }
}