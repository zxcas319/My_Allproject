def sumDivisor(num):
    
    a=sum([i for i in range(1,num+1) if num%i==0])

    return a

# 아래는 테스트로 출력해 보기 위한 코드입니다.
print(sumDivisor(12))