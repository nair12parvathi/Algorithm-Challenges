"""
file: babysit.py
language: python3
author: pan7447@rit.edu Parvathi Nair
author: vpb8262 Vishal Bulchandani

"""
"""
To compute the maximum pay a brother and sister can earn considering jobs that they can work on
together or separately depending on the number of children to babysit

"""
from operator import *

class Job:
    """
    Job class which stores the attributes of the jobs
    """
    def __init__(self, day, startTime, endTime, noOfChildren, hourlyRate):
        self.day=day
        self.startTime=startTime
        self.endTime=endTime
        self.noOfChildren=noOfChildren
        self.hourlyRate=hourlyRate
        self.value=(endTime-startTime)/100*hourlyRate

    def __str__(self):
        return  str(self.day)+ " " + str(self.startTime) + " "+ str(self.endTime) + " " +str(self.noOfChildren) + " " + str(self.hourlyRate)+ " " + str(self.value)

#total is global variable
total = 0
def takeInput():
    """
            Takes input from the console and creates objects and stores in a list jobList
            :return: jobList-list in which input is stored as objects
        """
    n=int(input())
    jobList=[]

    #taking n inputs and creating objects
    for i in range (n):
        str = input().strip('\n').split(" ")
        if int(str[1])>=600 and int(str[2])<=2300:
            jobs=Job (int(str[0]),int(str[1]),int(str[2]),int(str[3]),int(str[4]))
            jobList.append(jobs)
    return jobList

def sortInputByEndTimeAndDay(jobList):
    """
            Sorts the jobList based on day and then the endTime
            :param jobList: list of jobs
            :return: jobList in a sorted manner with respect to day and endTime
        """
    jobList=sorted(jobList, key= attrgetter('day','endTime'))
    return jobList


def divideJobs(jobList, maximum):
    """
            Segregates the jobs into list of lists with respect to day, that is jobs done in a particular day is stored in a single index.
            :param jobList: sorted jobLists
            :param maximum: the maximum amongst the days being considered
            :return: segregatedJobs which is a list of lists
        """

    segregatedJobs=[[0]]*(maximum)

    temp=jobList[0].day
    j = 0
    for i in range(0,len(jobList)):
        if jobList[i].day==temp:
            segregatedJobs[j].append(jobList[i])

        else:
            temp = jobList[i].day
            j += 1
            segregatedJobs[j]=[0,jobList[i]]

    return segregatedJobs

def computeRho(segregatedJob):
    """
        To compute the Roh value in a list
        :param segregatedJob: jobs done in a particular day
        :return: rho: list in which computed rho is stored
    """

    #inserting 0 at the 1st position
    rho = [0]
    count = 0

    #calculating rho
    for i in range(1,len(segregatedJob)):
        j = i-1
        while(j>0):
            if segregatedJob[i].startTime >= segregatedJob[j].endTime:
                count += 1
                rho.append(j)
                break
            j=j-1
        if count == 0:
            rho.append(0)
        count = 0


    return rho


def algo(segregatedJob):
    """
    Implementing the interval scheduling algorithm
    :param segregatedJob: A sorted list of jobs of one particular day
    :return: None
    """
    global total
    rho = computeRho(segregatedJob)
    r = len(rho);

    S = [[0 for x in range(r)] for y in range(r)]
    k = 0
    #implementaion of scheduling algorithm
    while(k<len(S)):
        for j in range(k, len(S)):
            if k == j and j != 0 and segregatedJob[j].noOfChildren < 4:
                S[j][k] = max(segregatedJob[j].value + S[rho[j]][k - 1], S[j - 1][k - 1])

            elif j > k and j != 0 and segregatedJob[j].noOfChildren >= 4:
                S[j][k] = S[j - 1][k]

            elif k == j and j != 0 and segregatedJob[j].noOfChildren >= 4:
                S[j][k] = max(segregatedJob[j].value + S[rho[j]][rho[k]], S[j - 1][k - 1])

            elif j > k and j != 0 and segregatedJob[j].noOfChildren < 4:
                S[j][k] = max(segregatedJob[j].value + S[rho[j]][k], S[j - 1][k])
            else:
                pass
            S[k][j] = S[j][k]
        k += 1
    length = len(S)

    #Adding the max pay for every individual field in the matrix
    total += S[length-1][length-1]

def main():
    """
        Main function.
        return: None
    """
    global total
    jobList=takeInput()
    jobListSorted=sortInputByEndTimeAndDay(jobList)
    maximum=jobListSorted[len(jobListSorted)-1].day
    segregatedJobs=divideJobs(jobListSorted, maximum)
    for i in range (len(segregatedJobs)):
        algo(segregatedJobs[i])

    # print the total pay
    print(int(total))

if __name__ == '__main__':
    main()