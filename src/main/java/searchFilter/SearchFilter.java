package searchFilter;

import dbstorage.model.Animal;

public abstract class SearchFilter {
    // 템플릿 메서드
    public final boolean filter(Animal animal) {
        return matches(animal);
    }

    // 검색 조건을 하위 클래스에서 정의
    protected abstract boolean matches(Animal animal);
}
