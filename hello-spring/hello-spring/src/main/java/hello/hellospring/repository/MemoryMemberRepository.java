package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    /*키를 Long, 값을 Member객체로 하는 해시맵 store 생성*/
    private static long sequence = 0L;

    @Override
    public Member save(Member member) { /*member객체를 매개변수로 하는 save는*/
        member.setId(++sequence); /*member의 id를 sequence에 1을 더한 값으로 저장*/
        store.put(member.getId(), member); /*해시맵에 키를 member의 id값으로, 값을 member객체로 저장*/
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { /*id를 매개변수로 하는 findById는*/
        return Optional.ofNullable(store.get(id)); /*해시맵에서 키가 id인 값(member객체)을 반환하는데*/
        /*그 값이 null일 수 있으므로 Optional로 담은뒤 반환함*/
    }

    @Override
    public Optional<Member> findByName(String name) { /*name을 매개변수로 하는 findByName은*/
        return store.values().stream() /*해시맵의 값을 모두 모아놓은 리스트를 스트림으로 만들어서*/
                .filter(member -> member.getName().equals(name)) 
                //그 요소들중 name값이 매개변수로 들어온 name과 일치하는 요소만 남기고
                .findAny(); //그중에 하나를 반환 ( 값이 하나뿐이므로 유일한 하나만 반환되게 됨)
    }

    @Override
    public List<Member> findAll() { 
        return new ArrayList<>(store.values());
        //해시맵의 모든값을 요소로 하는 List의 모든 요소를 요소로 하는 ArrayList반환
    }

    public void clearStore(){
        store.clear(); //해시맵을 초기화, Test가 끝날때마다 이 메서드를 이용해 초기화시킬예정
    }
}
