package Game;

/**
 * It is the similar design to C#'s Func<>
 * Refer: https://docs.microsoft.com/en-us/dotnet/api/system.func-2?view=net-5.0'
 * @param <TResult>
 * @param <TIn>
 */
public interface IFunc<TResult, TIn>
{
    public TResult invoke(TIn in);
}
