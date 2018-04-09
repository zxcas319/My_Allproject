def no_continuous(s):
    # 함수를 완성하세요
    answer=[]
    answer.append(s[0])
    for i in s:
        if answer[-1]!=i:
            answer.append(i)
        
    return answer

# 아래는 테스트로 출력해 보기 위한 코드입니다.
print( no_continuous( "133303" ))