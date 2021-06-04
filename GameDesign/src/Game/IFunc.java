package Game;

/**
 * 类似于C#的Func设计
 * @param <TResult>
 * @param <TIn>
 */
public interface IFunc<TResult, TIn>
{
    public TResult invoke(TIn in);
}
