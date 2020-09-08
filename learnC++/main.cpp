#include <algorithm>
#include <cstdlib>
#include <iostream>
#include <iterator>
#include <vector>
#include <string>
#include <map>
#include <unordered_map>
#include <stack>
#include <set>
#include <cmath>
#include <list>
#include <memory>

using namespace std;



int main(){




    return EXIT_SUCCESS;
}

// ---------------------------------------------------------------------------------------------------------------------------------


int digital_root(int n)
{
    string sn = to_string(n);
    if(sn.size() > 1){
        int res = 0;
        for (char i : sn) {
            res += i - '0';
        }
        return digital_root(res);
    }
    return n;
}

// ---------------------------------------------------------------------------------------------------------------------------------

std::vector<std::string> wave(std::string y){
    std::vector<std::string> res;
    for(int i = 0; i < y.length(); ++i){
        if(isalpha(y[i])){
            y[i] = toupper(y[i]);
            res.emplace_back(y);
        }
    }
    return res;
}


// ---------------------------------------------------------------------------------------------------------------------------------


class adder {
public:
    int n;
    explicit adder(int n){
        this->n= n;
    }

    adder operator()(int next) const {
        return adder(this->n + next);
    }

    adder operator+ (int i) const
    {
        return adder(this->n + i);
    }
    adder operator- (int i) const
    {
        return adder(this->n - i);
    }
    bool operator==(const adder& lhs) const {
        return this->n == lhs.n;
    }
    bool operator==(const int& lhs) const {
        return this->n == lhs;
    }
};

bool operator==(const int& lhs, const adder& rhs)  {
    return lhs == rhs.n;
}

std::ostream &operator<<(std::ostream &os, adder const &m) {
    return os << m.n;
}

auto add(int n){


    if(adder(4) == 5){

    }
    return adder(n);
}



// ---------------------------------------------------------------------------------------------------------------------------------

string rot13(const string& msg)
{
    string sub = "NOPQRSTUVWXYZABCDEFGHIJKLM[\\]^_`nopqrstuvwxyzabcdefghijklm";
    string res;
    for(char c : msg){
        if(sub.find(c) != -1) res += sub[((int)c) - 65];
        else res += c;
    }
    return res;
}

// ---------------------------------------------------------------------------------------------------------------------------------

std::vector<std::vector<int> > matrixAddition(const std::vector<std::vector<int> >& a,std::vector<std::vector<int> > b){
    std::vector<std::vector<int>> res(a.size(), std::vector<int>(b.size(), 0));
    for (int i = 0; i < a.size(); ++i)
        for (int j = 0; j < b.size(); ++j)
            res[i][j] =  b[i][j];
    return res;
}


// ---------------------------------------------------------------------------------------------------------------------------------


template<class TYPE>
int getLengthOfMissingArray(std::vector<std::vector<TYPE>> arrayOfArrays)
{
    using namespace std;

    if(arrayOfArrays.empty()){
        return 0;
    }

    int min = arrayOfArrays[0].size();
    int sizeOfAll = 0;
    int shouldBe = 0;
    for(const auto& sub: arrayOfArrays){
        sizeOfAll += sub.size();
        if(sub.size() < min){
            min = sub.size();
        }
    }
    for (int i = min; i <= arrayOfArrays.size() + 1; ++i) {
        shouldBe+=i;
    }
    return shouldBe - sizeOfAll;
}

// ---------------------------------------------------------------------------------------------------------------------------------

std::string createPhoneNumber(const int arr [10]){
    std::string number = "(";
    for (int i = 0; i < 10; ++i) {
        if(i == 2){
            number += ")";
        }
        if(i == 7){
            number += "-";
        }
        number+=std::to_string(arr[i]);
    }
    return number;
}

bool isPrime(int n){
    if (n < 2) return false;
    for (int i = 2; i < sqrt(n - 1) + 1; ++i) {
        if(n % i == 0 ) return false;
    }
    return true;
}

unsigned long long dominantPrimesOfRange(int a, int b){
    using namespace std;
    unsigned long long result = 0;
    vector<int> primes;
    for (int i = 0; i <= b; ++i) {
        if(isPrime(i)){
            primes.emplace_back(i);
        }
    }

    for (int i = 1; i < primes.size()+1; ++i) {
        if(primes[i - 1] >= a && isPrime(i)){
            result += primes[i-1];
        }
    }

    return result;
}

// ---------------------------------------------------------------------------------------------------------------------------------


std::string cleanString(const std::string &s) {
    using namespace std;
    const char backspace = '#';
    string result;
    for (char j : s)
        if(j == backspace) result = result.substr(0, result.size() - 1);
        else result += j;
    return result;
}

// ---------------------------------------------------------------------------------------------------------------------------------

std::vector<std::string> uniq(const std::vector<std::string> &v) {
    std::vector<std::string> done;
    for (int i = 0; i < v.size(); ++i)
        if(v[i] != v[i + 1])
            done.emplace_back(v[i]);
    return done;
}

// ---------------------------------------------------------------------------------------------------------------------------------

std::vector<std::vector<int>> multiplication_table(int n){
    std::vector<std::vector<int>> table(n, std::vector<int>(n, 0));
    for (int i = 1; i <= n; ++i)
        for (int j = 1; j <= n; ++j)
            table[i][j] = i * j;
    return table;
}


// ---------------------------------------------------------------------------------------------------------------------------------

std::map<char, int> getAlphabetMap(){
    std::map<char, int> alphabet;
    for (int i = 97; i <= 122; ++i)
        alphabet[(char)i]= 0;
    return alphabet;
}

std::string missing_alphabets(const std::string &s) {
    std::map<char, int> alphabet = getAlphabetMap();
    std::string missing;
    int alphabetsCount = 0;
    for (char i : s)
        alphabetsCount = std::max(alphabetsCount, alphabet[i]++);
    for(auto entry: alphabet)
        if(entry.second < alphabetsCount)
            for (int i = 0; i < alphabetsCount - entry.second; ++i)
                missing += entry.first;
    return missing;
}

// ---------------------------------------------------------------------------------------------------------------------------------

int maxSequence(const std::vector<int>& arr){
    using namespace std;
    int max = 0;
    vector<vector<int>> results;
    for (int i = 0; i < arr.size(); ++i) {
        int max4Current = 0;
        int res = 0;
        for (int j = i; j < arr.size(); ++j)
            res += arr[j];
        if(res > max4Current)
            max4Current = res;
        if(max4Current > max)
            max = max4Current;
    }
    return max;
}


// ---------------------------------------------------------------------------------------------------------------------------------

std::vector<std::vector<int>> matrix_multiplication(std::vector<std::vector<int>> &a, std::vector<std::vector<int>> &b, size_t n){
    using namespace std;
    vector<vector<int>> res;
    for (int i = 0; i < a.size(); ++i) {
        vector<int> row;
        for (int j = 0; j < a.size(); ++j) {
            int currentValue = 0;
            for (int k = 0; k < n; ++k) {
                currentValue+= a[i][k] * b[k][j];
            }
            row.emplace_back(currentValue);
        }
        res.emplace_back(row);
    }
    return res;
}

// ---------------------------------------------------------------------------------------------------------------------------------


int getNumberLength(std::string s){
    std::string current(1,s[0]);
    int len = 0;
    for (unsigned int i = 1; i < s.length(); ++i){
        if(current.length() + i > s.length() || current.length() > 15 ) break;
        if(stoll(current) + 1 == stoll(s.substr(i, current.length())) ||
           stoll(current) + 2 == stoll(s.substr(i, current.length() + 1)) ||
           stoll(current) + 1 == stoll(s.substr(i, current.length() + 2)) ||
           stoll(current) + 2 == stoll(s.substr(i, current.length())))
        {
            len = s.substr(i, current.length()).length();
        }
        current += s[i];
    }

    return len;
}

long long missing(const std::string& in) {
    using namespace std;
    int step = getNumberLength(in);
    string test = in;
    if(step == 0) return -1;
    long long start = stoll(in.substr(0, step));
    std::vector<long long> missingNums;
    while (to_string(start).length() <= test.length()){
        string temp = test.substr(0, to_string(start).length());
        int res = temp.find(to_string(start));
        if(res != -1) test = test.substr(temp.length(), test.size());
        else missingNums.emplace_back(start);
        start++;
    }
    return missingNums.size() > 1 || missingNums.empty() ? -1 : missingNums[0];
}

// ---------------------------------------------------------------------------------------------------------------------------------


std::vector<std::string> parseInstruction(const std::string& instruction){
    using namespace std;
    string current;
    vector<string> res;
    for(char c : instruction){
        if(c != ' '){
            current+=c;
        } else {
            res.emplace_back(current);
            current = "";
        }
    }
    res.emplace_back(current);
    return res;
}


int getRegisterOrConst(std::unordered_map<std::string, int>& regs, const std::string& arg){
    using namespace std;
    if(regs.count(arg) > 0){
        return regs[arg];
    }
    return stoi(arg);
}

std::unordered_map<std::string, int> assembler(const std::vector<std::string>& program)
{

    using namespace std;
    unordered_map<std::string, int> registers;

    for(unsigned int i = 0; i < program.size(); ++i){
        vector<string> instruction = parseInstruction(program[i]);
        string command = instruction[0];
        string firstArg = instruction[1];
        string secondArg;
        if(command == "mov"){
            secondArg = instruction[2];
            registers[firstArg] = getRegisterOrConst(registers, secondArg);
        } else if(command == "inc"){
            registers[firstArg] = getRegisterOrConst(registers, firstArg) + 1;
        } else if(command == "dec"){
            registers[firstArg] = getRegisterOrConst(registers, firstArg) - 1;
        } else if(command == "jnz"){
            secondArg = instruction[2];
            int jumpPos = getRegisterOrConst(registers, secondArg);
            if(getRegisterOrConst(registers, firstArg) != 0){
                i += jumpPos - 1;
            }
        }
        else throw std::runtime_error("Invalid syntax on line :" + std::to_string(i));

    }
    return registers;
}

// ---------------------------------------------------------------------------------------------------------------------------------

bool is_closing(char brace, char brace2) {
    std::map<char, char> allowedBraces = {
            {'[', ']'},
            {'{', '}'},
            {'(', ')'}
    };
    return allowedBraces[brace] == brace2;
}

bool valid_braces(const std::string& braces)
{
    std::stack<char> f;
    for(char brace : braces)
        if(!f.empty() && is_closing(f.top(), brace)) f.pop();
        else f.push(brace);
    return f.empty();
}