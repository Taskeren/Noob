package cn.taskeren.noob.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class NoobRandom {

	private final Random r;

	public NoobRandom() {
		this.r = new Random();
	}

	public NoobRandom(long seed) {
		this.r = new Random(seed);
	}

	public int nextInt(int bound) {
		return r.nextInt(bound);
	}

	public <E> E next(List<E> list) {
		final int index = r.nextInt(list.size());
		return list.get(index);
	}

	@SafeVarargs
	public final <E> E next(List<E>... lists) {
		List<E> cache = Lists.newArrayList();
		for(List<E> list : lists) {
			cache.addAll(list);
		}
		return next(cache);
	}

	public <E> E next(E[] arr) {
		final int index = r.nextInt(arr.length);
		return arr[index];
	}

}
